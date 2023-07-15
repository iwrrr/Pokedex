package com.hwaryun.network

import com.hwaryun.network.model.PokemonInfoDto
import com.hwaryun.network.model.PokemonMoveDto
import com.hwaryun.network.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexApi {

    @GET(NetworkConstants.Pokemon.pokemon)
    suspend fun fetchPokemonList(
        @Query("page") page: Int,
        @Query("limit") limit: String? = PageSize.toString(),
        @Query("offset") offset: String? = (page * PageSize).toString(),
    ): PokemonResponse

    @GET(NetworkConstants.Pokemon.pokemonByName)
    suspend fun fetchPokemonByName(@Path("name") name: String): PokemonInfoDto

    @GET(NetworkConstants.Pokemon.moveByName)
    suspend fun fetchPokemonMove(@Path("name") name: String): PokemonMoveDto

    companion object {
        private const val PageSize = 20
    }
}