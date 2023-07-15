package com.hwaryun.data.repository

import com.hwaryun.common.DataResult
import com.hwaryun.database.model.PokemonEntity
import com.hwaryun.database.model.PokemonInfoEntity
import com.hwaryun.network.model.PokemonInfoDto
import com.hwaryun.network.model.PokemonMoveDto
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(page: Int): Flow<DataResult<List<PokemonEntity>>>
    fun getPokemonByName(name: String): Flow<DataResult<PokemonInfoDto>>
    fun getPokemonMove(name: String): Flow<DataResult<PokemonMoveDto>>
    fun catchOrReleasePokemon(name: String, isCatched: Boolean): Flow<DataResult<Unit>>
    fun getCatchedPokemons(): Flow<DataResult<List<PokemonInfoEntity>>>
}