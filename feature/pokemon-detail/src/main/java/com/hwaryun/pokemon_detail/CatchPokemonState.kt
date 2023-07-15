package com.hwaryun.pokemon_detail

data class CatchPokemonState(
    val showDialog: Boolean = false,
    val isCatched: Boolean = false,
    val isLoading: Boolean = false,
    val failed: Boolean = false,
)