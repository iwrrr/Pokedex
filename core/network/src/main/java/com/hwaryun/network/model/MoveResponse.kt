package com.hwaryun.network.model


import com.google.gson.annotations.SerializedName

data class MoveResponse(
    @SerializedName("move")
    val move: Move? = null,
    @SerializedName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail?>? = null
) {
    data class Move(
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("url")
        val url: String? = null
    )

    data class VersionGroupDetail(
        @SerializedName("level_learned_at")
        val levelLearnedAt: Int? = null,
        @SerializedName("move_learn_method")
        val moveLearnMethod: MoveLearnMethod? = null,
        @SerializedName("version_group")
        val versionGroup: VersionGroup? = null
    ) {
        data class MoveLearnMethod(
            @SerializedName("name")
            val name: String? = null,
            @SerializedName("url")
            val url: String? = null
        )

        data class VersionGroup(
            @SerializedName("name")
            val name: String? = null,
            @SerializedName("url")
            val url: String? = null
        )
    }
}