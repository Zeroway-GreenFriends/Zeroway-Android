package com.greenfriends.zeroway.data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email") val email: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("provider") val provider: String
)

data class Result(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String,
)

data class AuthResponse(
    @SerializedName("result") val result: Result
)