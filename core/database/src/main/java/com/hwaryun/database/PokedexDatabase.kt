package com.hwaryun.database

import androidx.room.Database
import com.hwaryun.database.model.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokedexDatabase