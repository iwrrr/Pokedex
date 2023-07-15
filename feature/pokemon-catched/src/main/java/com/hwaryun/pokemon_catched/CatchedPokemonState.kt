package com.hwaryun.pokemon_catched

import com.hwaryun.domain.model.Pokemon

data class CatchedPokemonState(
    val pokemonList: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)