package com.hwaryun.network.model

import com.google.gson.annotations.SerializedName

data class PokemonInfoDto(
    val id: Long,
    val name: String,
    val height: Long,
    val weight: Long,
    @SerializedName("base_experience")
    val experience: Long,
    val moves: List<MoveResponse>,
    val types: List<TypeResponse>,
    val stats: List<StatsResponse>,
    val isCatched: Boolean = false,
) {
    data class MoveResponse(
        @SerializedName("move")
        val move: InfoResponse = InfoResponse(),
        @SerializedName("version_group_details")
        val versionGroupDetails: List<VersionGroupDetail>
    )

    data class VersionGroupDetail(
        @SerializedName("level_learned_at")
        val levelLearnedAt: Int,
        @SerializedName("move_learn_method")
        val moveLearnMethod: InfoResponse = InfoResponse(),
        @SerializedName("version_group")
        val versionGroup: InfoResponse = InfoResponse()
    )

    data class TypeResponse(
        val slot: Int,
        val type: InfoResponse = InfoResponse()
    )

    data class StatsResponse(
        @SerializedName("base_stat")
        val value: Int,
        val stat: InfoResponse = InfoResponse()
    )

}

