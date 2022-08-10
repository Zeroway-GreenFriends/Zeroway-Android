package com.greenfriends.zeroway

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName(value = "profileImg") var profileImg: String,
    @SerializedName(value = "signInReq") var signInReq: SingInReq
)

data class SingInReq(
    @SerializedName(value = "email") var email: String,
    @SerializedName(value = "nickname") var nickname: String,
    @SerializedName(value = "provider") var provider: String
)