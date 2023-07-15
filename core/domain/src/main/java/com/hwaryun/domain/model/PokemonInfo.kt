package com.hwaryun.domain.model


data class PokemonInfo(
    val id: Long,
    val name: String,
    val height: Long,
    val weight: Long,
    val experience: Long,
    val moves: List<Move>,
    val types: List<Info>,
    val stats: List<Stats>,
    val isCatched: Boolean = false
) {
    val idString
        get() = when (id.toString().length) {
            1 -> "#00$id"
            2 -> "#0$id"
            else -> "#$id"
        }

    val imageUrl: String =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

    data class Move(
        val name: String,
        val url: String,
        val details: List<VersionGroupDetail>
    )

    data class VersionGroupDetail(
        val levelLearnedAt: Int,
        val moveLearnMethod: Info,
        val versionGroup: Info
    )

    data class Type(
        val name: String,
        val slot: Int
    )

    data class Stats(
        val value: Int,
        val stat: Info
    ) {
        val maxValue: Int
            get() = when (stat.name) {
                "hp" -> maxHp
                "attack" -> maxAttack
                "defense" -> maxDefense
                "special-attack" -> maxSpAttack
                "special-defense" -> maxSpDefense
                "speed" -> maxSpeed
                else -> value
            }

        val name: String
            get() = when (stat.name) {
                "hp" -> "HP"
                "attack" -> "Attack"
                "defense" -> "Defense"
                "special-attack" -> "Sp. Atk"
                "special-defense" -> "Sp. Dep"
                "speed" -> "Speed"
                else -> stat.name
            }
    }

    companion object {
        const val maxHp = 255
        const val maxAttack = 190
        const val maxDefense = 230
        const val maxSpAttack = 200
        const val maxSpDefense = 230
        const val maxSpeed = 180
    }
}

