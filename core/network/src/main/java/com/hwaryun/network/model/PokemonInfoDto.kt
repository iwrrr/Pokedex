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
    val stats: List<StatsResponse>
) {
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
  )

  data class Stat(
    val name: String
  )
}
