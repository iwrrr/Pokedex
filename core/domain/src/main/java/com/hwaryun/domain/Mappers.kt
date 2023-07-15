package com.hwaryun.domain

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hwaryun.database.model.PokemonEntity
import com.hwaryun.database.model.PokemonInfoEntity
import com.hwaryun.domain.model.Pokemon
import com.hwaryun.domain.model.PokemonInfo

fun PokemonEntity.toPokemon(page: Int) = Pokemon(
    page = page,
    name = name,
    url = url
)

fun PokemonInfoEntity.toPokemonInfo(): PokemonInfo {
    return PokemonInfo(
        id = id,
        name = name,
        height = height,
        weight = weight,
        experience = experience,
        types = Gson().fromJson(
            types,
            object : TypeToken<List<PokemonInfo.TypeResponse>>() {}.type
        ),
        stats = Gson().fromJson(
            stats,
            object : TypeToken<List<PokemonInfo.StatsResponse>>() {}.type
        ),
        isCatched = isCatched
    )
}

fun PokemonInfoEntity.toPokemon(number: Long): Pokemon {
    return Pokemon(
        name = name,
        url = "https://pokeapi.co/api/v2/pokemon/$number/"
    )
}