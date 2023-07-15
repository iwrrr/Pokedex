package com.hwaryun.domain.model

data class PokemonMove(
    val id: Int = -1,
    val name: String = "",
    val accuracy: Int = 0,
    val damageClass: Info = Info(),
    val learnedPokemon: List<Pokemon> = emptyList(),
    val machines: Info = Info(),
    val power: Int = 0,
    val pp: Int = 0,
    val type: Info = Info()
)