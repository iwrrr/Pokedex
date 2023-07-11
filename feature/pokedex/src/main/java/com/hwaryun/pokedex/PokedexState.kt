package com.hwaryun.pokedex

import com.hwaryun.domain.model.Pokemon

data class PokedexState(
    val pokemonList: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val isLastPageLoaded: Boolean = false,
    val error: String? = null,
)