package com.hwaryun.domain

import com.hwaryun.database.model.PokemonEntity

fun PokemonEntity.toPokemon(page: Int) = Pokemon(
    page = page,
    name = name,
    url = url
)