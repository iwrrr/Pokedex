package com.hwaryun.network.model

import com.google.gson.annotations.SerializedName

data class PokemonInfoDto(
  val id: Long,
  val name: String,
  val height: Long,
  val weight: Long,
  @SerializedName("base_experience")
  val experience: Long,
  val types: List<TypeResponse>,
  val stats: List<StatsResponse>,
  val isFavorite: Boolean = false
) {
  val idString
    get() = when (id.toString().length) {
      1 -> "#00$id"
      2 -> "#0$id"
      else -> "#$id"
    }

  val imageUrl: String =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

  data class TypeResponse(
    val slot: Int,
    val type: Type
  )

  data class Type(
    val name: String
  )

  data class StatsResponse(
    @SerializedName("base_stat")
    val value: Int,
    val stat: Stat
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

  data class Stat(
    val name: String
  )

  companion object {
    const val maxHp = 255
    const val maxAttack = 190
    const val maxDefense = 230
    const val maxSpAttack = 200
    const val maxSpDefense = 230
    const val maxSpeed = 180
  }
}
