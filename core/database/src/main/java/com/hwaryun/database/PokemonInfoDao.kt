package com.hwaryun.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hwaryun.database.model.PokemonInfoEntity

@Dao
interface PokemonInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: PokemonInfoEntity)

    @Query("SELECT * FROM pokemon_infos WHERE name = :name LIMIT 1")
    suspend fun getPokemonByName(name: String): PokemonInfoEntity?

    @Query("UPDATE pokemon_infos SET isCatched = :isCatched WHERE name = :name")
    suspend fun catchOrReleasePokemon(name: String, isCatched: Boolean)

    @Query("SELECT * FROM pokemon_infos WHERE isCatched = 1")
    suspend fun getCatchedPokemons(): List<PokemonInfoEntity>
}