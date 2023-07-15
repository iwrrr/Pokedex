package com.hwaryun.data.repository

import com.hwaryun.common.DataResult
import com.hwaryun.common.ext.execute
import com.hwaryun.common.ext.proceed
import com.hwaryun.data.toPokemonEntity
import com.hwaryun.data.toPokemonInfoDto
import com.hwaryun.data.toPokemonInfoEntity
import com.hwaryun.data.toPokemonMoveDto
import com.hwaryun.data.toPokemonMoveEntity
import com.hwaryun.database.PokemonDao
import com.hwaryun.database.PokemonInfoDao
import com.hwaryun.database.PokemonMoveDao
import com.hwaryun.database.model.PokemonEntity
import com.hwaryun.database.model.PokemonInfoEntity
import com.hwaryun.network.PokedexApi
import com.hwaryun.network.model.PokemonInfoDto
import com.hwaryun.network.model.PokemonMoveDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokedexApi: PokedexApi,
    private val pokemonDao: PokemonDao,
    private val pokemonInfoDao: PokemonInfoDao,
    private val pokemonMoveDao: PokemonMoveDao
) : PokemonRepository {

    override fun getPokemonList(page: Int): Flow<DataResult<List<PokemonEntity>>> = flow {
        val cachedPokemonList = pokemonDao.getPokemonsByPage(page)

        if (cachedPokemonList.isEmpty()) {
            val response = execute { pokedexApi.fetchPokemonList(page = page) }
            response.value?.results
                ?.map { it.toPokemonEntity(page) }
                ?.let { pokemonDao.insert(it) }

            emit(proceed { pokemonDao.getPokemonsByPage(page) })
        } else {
            emit(proceed { cachedPokemonList })
        }
    }

    override fun getPokemonByName(name: String): Flow<DataResult<PokemonInfoDto>> = flow {
        val cachedPokemon = pokemonInfoDao.getPokemonByName(name)

        if (cachedPokemon == null) {
            val response = execute { pokedexApi.fetchPokemonByName(name) }
            response.value
                ?.toPokemonInfoEntity()
                ?.let { pokemonInfoDao.insert(it) }

            pokemonInfoDao.getPokemonByName(name)?.let {
                emit(proceed(it::toPokemonInfoDto))
            }
        } else {
            emit(proceed(cachedPokemon::toPokemonInfoDto))
        }
    }

    override fun getPokemonMove(name: String): Flow<DataResult<PokemonMoveDto>> = flow {
        val cachedMove = pokemonMoveDao.getPokemonMoveByName(name)

        if (cachedMove == null) {
            val response = execute { pokedexApi.fetchPokemonMove(name) }
            response.value
                ?.toPokemonMoveEntity()
                ?.let { pokemonMoveDao.insert(it) }

            pokemonMoveDao.getPokemonMoveByName(name)?.let {
                emit(proceed(it::toPokemonMoveDto))
            }
        } else {
            emit(proceed(cachedMove::toPokemonMoveDto))
        }
    }

    override fun catchOrReleasePokemon(name: String, isCatched: Boolean): Flow<DataResult<Unit>> =
        flow {
            emit(proceed { pokemonInfoDao.catchOrReleasePokemon(name, isCatched) })
        }

    override fun getCatchedPokemons(): Flow<DataResult<List<PokemonInfoEntity>>> = flow {
        emit(proceed { pokemonInfoDao.getCatchedPokemons() })
    }
}