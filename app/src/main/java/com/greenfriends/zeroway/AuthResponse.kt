package com.greenfriends.zeroway

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName(value = "isSuccess") val isSuccess: Boolean,
    @SerializedName(value = "code") val code: Int,
    @SerializedName(value = "message") val message: String,
    @SerializedName(value = "result") val result: Result?
)

data class Result(
    @SerializedName(value = "accessToken") var accessToken: String,
    @SerializedName(value = "refreshToken") var refreshToken: String,
    @SerializedName(value = "newUser") var newUser: Boolean
)
