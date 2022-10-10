package com.greenfriends.zeroway.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class NoticeResponse(
    @SerializedName("announceId") val announceId: Long,
    @SerializedName("title") val title: String,
    @SerializedName("createdAt") val createdAt: String
)

data class NoticeDetailResponse(
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("createdAt") val createdAt: String
)