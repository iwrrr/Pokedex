package com.hwaryun.data.repository

import com.hwaryun.common.DataResult
import com.hwaryun.common.ext.execute
import com.hwaryun.common.ext.proceed
import com.hwaryun.data.toPokemonEntity
import com.hwaryun.database.PokemonDao
import com.hwaryun.database.model.PokemonEntity
import com.hwaryun.network.PokedexApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface PokemonRepository {
    fun getPokemonList(page: Int): Flow<DataResult<List<PokemonEntity>>>
}

class PokemonRepositoryImpl @Inject constructor(
    private val pokedexApi: PokedexApi,
    private val pokemonDao: PokemonDao
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
}