package com.hwaryun.database.model

import androidx.room.Entity

@Entity(tableName = "pokemons")
data class PokemonEntity(
    var page: Long = 0,
    val name: String,
    val url: String
)