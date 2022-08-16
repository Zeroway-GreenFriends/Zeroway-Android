package com.greenfriends.zeroway.model

import com.google.gson.annotations.SerializedName

data class CommunityResponse(
    @SerializedName("data") val communityPosts: List<CommunityPost>
)

data class CommunityPost(
    @SerializedName("postId") val postId: Long,
    @SerializedName("content") val content: String,
    @SerializedName("challenge") val challenge: Boolean,
    @SerializedName("username") val username: String,
    @SerializedName("userProfileImg") val userProfileImg: String?,
    @SerializedName("likeCount") val likeCount: Int,
    @SerializedName("commentCount") val commentCount: Int,
    @SerializedName("liked") val liked: Boolean,
    @SerializedName("bookmarked") val bookmarked: Boolean,
    @SerializedName("imageList") val imageList: List<String>
)

data class CommunityPostRegisterContentRequest(
    @SerializedName("content") val content: String,
    @SerializedName("challenge") val isChallenge: Boolean
)

data class CommunityPostDeleteResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)

data class CommunityPostLikeListResponse(
    @SerializedName("totalCount") val totalCount: Int,
    @SerializedName("likeList") val communityPostLikeList: CommunityPostLikeList
)

data class CommunityPostLikeList(
    @SerializedName("userId") val userId: Long,
    @SerializedName("username") val userName: String,
    @SerializedName("userProfileImg") val userProfileImg: String,
    @SerializedName("level") val userLevel: Int
)

data class CommunityPostBookmarkResponse(
    @SerializedName("bookmark") val bookmark: Boolean
)

data class CommunityPostLikeResponse(
    @SerializedName("like") val like: Boolean
)

data class CommunityPostDetailResponse(
    @SerializedName("postId") val postId: Long,
    @SerializedName("username") val userName: String,
    @SerializedName("userProfileImg") val userProfileImg: String,
    @SerializedName("content") val content: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("challenge") val challenge: Boolean,
    @SerializedName("likeCount") val likeCount: Int,
    @SerializedName("commentCount") val commentCount: Int,
    @SerializedName("liked") val liked: Boolean,
    @SerializedName("bookmarked") val bookmarked: Boolean,
    @SerializedName("imageList") val imageList: List<String>,
    @SerializedName("commentList") val communityPostDetailCommentList: List<CommunityPostDetailCommentList>
)

data class CommunityPostDetailCommentList(
    @SerializedName("userId") val userId: Int,
    @SerializedName("username") val userName: String,
    @SerializedName("userProfileImg") val userProfileImg: String,
    @SerializedName("commentId") val commentId: Long,
    @SerializedName("content") val content: String,
    @SerializedName("createdAt") val createdAt: String, // LocalDataTime
    @SerializedName("likeCount") val likeCount: Int,
    @SerializedName("liked") val liked: Boolean
)