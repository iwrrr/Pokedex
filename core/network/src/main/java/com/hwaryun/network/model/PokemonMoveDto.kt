package com.hwaryun.network.model

import com.google.gson.annotations.SerializedName

data class PokemonMoveDto(
    val id: Int = 0,
    val name: String = "",
    val accuracy: Int = 0,
    @SerializedName("effect_chance") val effectChance: Int = 0,
    @SerializedName("damage_class") val damageClass: InfoResponse? = null,
    @SerializedName("learned_by_pokemon") val learnedPokemon: List<InfoResponse> = emptyList(),
    val machines: List<MachinesResponse> = emptyList(),
    val generation: InfoResponse,
    val power: Int = 0,
    val pp: Int = 0,
    val type: InfoResponse = InfoResponse()
)

data class MachinesResponse(
    val machine: InfoResponse,
    @SerializedName("version_group") val versionGroup: InfoResponse = InfoResponse()
)