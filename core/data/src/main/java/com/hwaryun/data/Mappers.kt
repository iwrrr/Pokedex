package com.hwaryun.data

import com.hwaryun.database.model.PokemonEntity
import com.hwaryun.network.model.PokemonDto

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

//fun PokemonInfo.toPokemonInfoEntity() = PokemonInfoEntity(
//    id = id,
//    name = name,
//    height = height,
//    weight = weight,
//    experience = experience,
//    types = Json.encodeToString(types),
//    stats = Json.encodeToString(stats),
//    isFavorite = if (isFavorite) 1 else 0
//)
//
//fun PokemonInfoEntity.toPokemonInfo() = PokemonInfo(
//    id = id,
//    name = name,
//    height = height,
//    weight = weight,
//    experience = experience,
//    types = Json.decodeFromString(types),
//    stats = Json.decodeFromString(stats),
//    isFavorite = isFavorite != 0L
//)
//
//fun PokemonInfoEntity.toPokemonDto() = PokemonDto(
//    name = name,
//    url = "$id/"
//)