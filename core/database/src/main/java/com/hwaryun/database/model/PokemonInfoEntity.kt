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
    val types: String,
    val stats: String,
    val isFavorite: Int = 0
)