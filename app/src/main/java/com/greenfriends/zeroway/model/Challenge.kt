package com.greenfriends.zeroway.model

import com.google.gson.annotations.SerializedName

data class ChallengeResponse(
    @SerializedName("nickname") val nickname: String,
    @SerializedName("level") val level: Int,
    @SerializedName("exp") val exp: Int,
    @SerializedName("imgUrl") val imgUrl: String
)

data class ChallengeListResponse(
    @SerializedName("challengeId") val challengeId: Long,
    @SerializedName("content") val content: String,
    @SerializedName("complete") val complete: Boolean
)

data class ChallengeLevelResponse(
    @SerializedName("level") val level: Int
)