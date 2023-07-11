package com.hwaryun.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hwaryun.database.model.PokemonEntity

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemons: List<PokemonEntity>)

    @Query("SELECT * FROM pokemons WHERE page = :page")
    suspend fun getPokemonsByPage(page: Int): List<PokemonEntity>
}