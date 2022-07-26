package com.greenfriends.zeroway.data.model

import com.google.gson.annotations.SerializedName

data class TipResponse(
    @SerializedName("tipNum") val tipNum: String,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String
)

data class TermResponse(
    @SerializedName("term") val term: String,
    @SerializedName("description") val description: String
)