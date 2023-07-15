package com.hwaryun.data.repository

import com.hwaryun.common.DataResult
import com.hwaryun.database.model.PokemonEntity
import com.hwaryun.database.model.PokemonInfoEntity
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(page: Int): Flow<DataResult<List<PokemonEntity>>>
    fun getPokemonByName(name: String): Flow<DataResult<PokemonInfoEntity>>
    fun catchOrReleasePokemon(name: String, isCatched: Boolean): Flow<DataResult<Unit>>
    fun getCatchedPokemons(): Flow<DataResult<List<PokemonInfoEntity>>>
}