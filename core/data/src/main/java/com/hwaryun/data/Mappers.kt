package com.hwaryun.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hwaryun.database.model.PokemonEntity
import com.hwaryun.database.model.PokemonInfoEntity
import com.hwaryun.database.model.PokemonMoveEntity
import com.hwaryun.network.model.InfoResponse
import com.hwaryun.network.model.PokemonDto
import com.hwaryun.network.model.PokemonInfoDto
import com.hwaryun.network.model.PokemonMoveDto

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

fun PokemonInfoEntity.toPokemonInfoDto() = PokemonInfoDto(
    id = id,
    name = name,
    height = height,
    weight = weight,
    experience = experience,
    moves = Gson().fromJson(
        moves,
        object : TypeToken<List<PokemonInfoDto.MoveResponse>>() {}.type
    ),
    types = Gson().fromJson(
        types,
        object : TypeToken<List<PokemonInfoDto.TypeResponse>>() {}.type
    ),
    stats = Gson().fromJson(
        stats,
        object : TypeToken<List<PokemonInfoDto.StatsResponse>>() {}.type
    ),
    isCatched = isCatched
)

fun PokemonInfoDto.toPokemonInfoEntity() = PokemonInfoEntity(
    id = id,
    name = name,
    height = height,
    weight = weight,
    experience = experience,
    moves = Gson().toJson(moves),
    types = Gson().toJson(types),
    stats = Gson().toJson(stats),
    isCatched = false
)

fun PokemonMoveEntity.toPokemonMoveDto() = PokemonMoveDto(
    id = id,
    name = name,
    accuracy = accuracy,
    effectChance = effectChance,
    damageClass = Gson().fromJson(
        damageClass,
        object : TypeToken<InfoResponse>() {}.type
    ),
    learnedPokemon = Gson().fromJson(
        learnedPokemon,
        object : TypeToken<List<InfoResponse>>() {}.type
    ),
    machines = Gson().fromJson(
        machines,
        object : TypeToken<List<InfoResponse>>() {}.type
    ),
    generation = Gson().fromJson(
        generation,
        object : TypeToken<InfoResponse>() {}.type
    ),
    power = power,
    pp = pp,
    type = Gson().fromJson(
        type,
        object : TypeToken<InfoResponse>() {}.type
    ),
)

fun PokemonMoveDto.toPokemonMoveEntity() = PokemonMoveEntity(
    id = id,
    name = name,
    accuracy = accuracy,
    effectChance = effectChance,
    damageClass = Gson().toJson(damageClass),
    learnedPokemon = Gson().toJson(learnedPokemon),
    machines = Gson().toJson(machines),
    generation = Gson().toJson(generation),
    power = power,
    pp = pp,
    type = Gson().toJson(type),
)