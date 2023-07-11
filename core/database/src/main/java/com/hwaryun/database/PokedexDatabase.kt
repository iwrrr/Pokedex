package com.hwaryun.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hwaryun.database.model.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokedexDatabase : RoomDatabase() {

    abstract val pokemonDao: PokemonDao
}