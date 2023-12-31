package com.hwaryun.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_infos")
data class PokemonInfoEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val height: Long,
    val weight: Long,
    val experience: Long,
    val moves: String,
    val types: String,
    val stats: String,
    val isCatched: Boolean = false
)