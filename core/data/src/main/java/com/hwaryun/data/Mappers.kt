package com.hwaryun.data

import com.google.gson.Gson
import com.hwaryun.database.model.PokemonEntity
import com.hwaryun.database.model.PokemonInfoEntity
import com.hwaryun.network.model.PokemonDto
import com.hwaryun.network.model.PokemonInfoDto

fun PokemonDto.toPokemonEntity(page: Int) = PokemonEntity(
    page = page,
    name = name,
    url = url
)

fun PokemonEntity.toPokemonDto() = PokemonDto(
    page = page,
    name = name,
    url = url
)

fun PokemonInfoDto.toPokemonInfoEntity() = PokemonInfoEntity(
    id = id,
    name = name,
    height = height,
    weight = weight,
    experience = experience,
    types = Gson().toJson(types),
    stats = Gson().toJson(stats),
    isFavorite = if (isFavorite) 1 else 0
)
//
//fun PokemonInfoEntity.toPokemonDto() = PokemonDto(
//    name = name,
//    url = "$id/"
//)