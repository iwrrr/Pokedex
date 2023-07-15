package com.hwaryun.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hwaryun.database.model.PokemonEntity
import com.hwaryun.database.model.PokemonInfoEntity
import com.hwaryun.database.model.PokemonMoveEntity

@Database(
    entities = [PokemonEntity::class, PokemonInfoEntity::class, PokemonMoveEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PokedexDatabase : RoomDatabase() {

    abstract val pokemonDao: PokemonDao
    abstract val pokemonInfoDao: PokemonInfoDao
    abstract val pokemonMoveDao: PokemonMoveDao
}