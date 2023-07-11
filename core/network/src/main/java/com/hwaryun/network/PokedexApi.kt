package com.hwaryun.network

import com.hwaryun.network.model.PokemonInfoDto
import com.hwaryun.network.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexApi {

    @GET(NetworkConstants.Pokemon.route)
    suspend fun fetchPokemonList(
        @Query("page") page: Int,
        @Query("limit") limit: String? = PageSize.toString(),
        @Query("offset") offset: String? = (page * PageSize).toString(),
    ): PokemonResponse

    @GET(NetworkConstants.Pokemon.byName)
    suspend fun fetchPokemonByName(@Path("name") name: String): PokemonInfoDto

    companion object {
        private const val PageSize = 20
    }
}