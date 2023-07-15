package com.hwaryun.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hwaryun.database.model.PokemonMoveEntity

@Dao
interface PokemonMoveDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(move: PokemonMoveEntity)

    @Query("SELECT * FROM pokemon_moves WHERE name = :name LIMIT 1")
    suspend fun getPokemonMoveByName(name: String): PokemonMoveEntity?

    @Query("SELECT * FROM pokemon_moves")
    suspend fun getPokemonMoves(): List<PokemonMoveEntity>
}