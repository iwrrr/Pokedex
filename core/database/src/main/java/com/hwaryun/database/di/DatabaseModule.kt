package com.hwaryun.database.di

import android.content.Context
import androidx.room.Room
import com.hwaryun.database.PokedexDatabase
import com.hwaryun.database.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePokedexDatabase(
        @ApplicationContext context: Context
    ): PokedexDatabase {
        return Room.databaseBuilder(context, PokedexDatabase::class.java, "pokedex.db")
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(database: PokedexDatabase): PokemonDao {
        return database.pokemonDao
    }
}