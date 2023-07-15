package com.hwaryun.domain

import com.hwaryun.database.model.PokemonEntity
import com.hwaryun.database.model.PokemonInfoEntity
import com.hwaryun.domain.model.Info
import com.hwaryun.domain.model.Pokemon
import com.hwaryun.domain.model.PokemonInfo
import com.hwaryun.domain.model.PokemonMove
import com.hwaryun.network.model.InfoResponse
import com.hwaryun.network.model.PokemonInfoDto
import com.hwaryun.network.model.PokemonMoveDto

fun PokemonEntity.toPokemon(page: Int) = Pokemon(
    page = page,
    name = name,
    url = url
)

fun PokemonInfoEntity.toPokemon(number: Long): Pokemon {
    return Pokemon(
        name = name,
        url = "https://pokeapi.co/api/v2/pokemon/$number/"
    )
}

//fun PokemonInfoEntity.toPokemonInfo(): PokemonInfo {
//    return PokemonInfo(
//        id = id,
//        name = name,
//        height = height,
//        weight = weight,
//        experience = experience,
//        moves = Gson().fromJson(
//            moves,
//            object : TypeToken<List<PokemonInfo.MoveResponse>>() {}.type
//        ),
//        types = Gson().fromJson(
//            types,
//            object : TypeToken<List<PokemonInfo.TypeResponse>>() {}.type
//        ),
//        stats = Gson().fromJson(
//            stats,
//            object : TypeToken<List<PokemonInfo.StatsResponse>>() {}.type
//        ),
//        isCatched = isCatched
//    )
//}

fun PokemonInfoDto.toPokemonInfo(): PokemonInfo {
    return PokemonInfo(
        id = id,
        name = name,
        height = height,
        weight = weight,
        experience = experience,
        moves = moves.map { it.toModel() },
        types = types.map { it.toModel() },
        stats = stats.map { it.toModel() },
        isCatched = isCatched
    )
}

fun PokemonInfoDto.StatsResponse.toModel(): PokemonInfo.Stats = with(this) {
    PokemonInfo.Stats(value = value, stat.toModel())
}

fun PokemonInfoDto.TypeResponse.toModel(): Info = with(this) {
    Info(name = type.name, id = type.url.parseId())
}

fun PokemonInfoDto.MoveResponse.toModel(): PokemonInfo.Move = with(this) {
    PokemonInfo.Move(
        name = move.name,
        url = move.url,
        details = versionGroupDetails.map {
            it.toModel()
        }
    )
}

fun PokemonInfoDto.VersionGroupDetail.toModel(): PokemonInfo.VersionGroupDetail = with(this) {
    PokemonInfo.VersionGroupDetail(
        levelLearnedAt = levelLearnedAt,
        moveLearnMethod = moveLearnMethod.toModel(),
        versionGroup = versionGroup.toModel()
    )
}

fun PokemonMoveDto.toModel(): PokemonMove = with(this) {
    PokemonMove(
        id = id,
        name = name,
        accuracy = accuracy,
        damageClass = damageClass?.toModel() ?: Info(),
        learnedPokemon = learnedPokemon.map {
            val info = it.toModel()
            Pokemon(info.id, info.name, getImageUrl(info.id))
        },
        power = power,
        pp = pp,
        type = type.toModel()
    )
}

fun InfoResponse.toModel(): Info = with(this) {
    Info(name, url.parseId())
}

fun String.parseId() = this.trimEnd('/').split("/").last().toInt()

private fun getImageUrl(id: Int): String =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"