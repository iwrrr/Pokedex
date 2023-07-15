package com.hwaryun.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_moves")
data class PokemonMoveEntity(
    @PrimaryKey
    val id: Int = 0,
    val name: String = "",
    val accuracy: Int = 0,
    val effectChance: Int = 0,
    val damageClass: String = "",
    val learnedPokemon: String = "",
    val machines: String = "",
    val generation: String = "",
    val power: Int = 0,
    val pp: Int = 0,
    val type: String = "",
)
