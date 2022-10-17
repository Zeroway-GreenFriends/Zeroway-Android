package com.greenfriends.zeroway.data.model

import com.google.gson.annotations.SerializedName

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

data class MyPostResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<MyPostList>
)

data class MyPostList(
    @SerializedName("profileImgUrl") val profileImgUrl: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("content") val content: String,
    @SerializedName("likeCount") val likeCount: Int,
    @SerializedName("commentCount") val commentCount: Int,
    @SerializedName("imgCount") val imgCount: Int,
    @SerializedName("isScraped") val isScraped: Boolean
)
