package com.hwaryun.pokemon_detail

import com.hwaryun.domain.model.PokemonInfo

data class PokemonDetailState(
    val pokemonInfo: PokemonInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)