package com.greenfriends.zeroway.data

import com.google.gson.annotations.SerializedName

data class TipResponse(
    val num: String,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String
)