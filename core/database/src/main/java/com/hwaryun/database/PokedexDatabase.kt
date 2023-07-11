package com.hwaryun.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hwaryun.database.model.PokemonEntity
import com.hwaryun.database.model.PokemonInfoEntity

@Database(entities = [PokemonEntity::class, PokemonInfoEntity::class], version = 1)
abstract class PokedexDatabase : RoomDatabase() {

    abstract val pokemonDao: PokemonDao
    abstract val pokemonInfoDao: PokemonInfoDao
}