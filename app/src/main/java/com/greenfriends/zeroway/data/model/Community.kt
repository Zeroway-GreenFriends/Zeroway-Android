package com.greenfriends.zeroway.data.model

import com.google.gson.annotations.SerializedName

data class CommunityPostRegisterContentRequest(
    @SerializedName("content") val content: String,
    @SerializedName("challenge") val isChallenge: Boolean,
    @SerializedName("review") val review: Boolean
)

data class CommunityPostCommentRequest(
    @SerializedName("content") val content: String
)

data class CommunityLikeRequest(
    @SerializedName("like") val like: Boolean
)

data class CommunityPostBookmarkRequest(
    @SerializedName("bookmark") val bookmark: Boolean
)

data class CommunityResponse(
    @SerializedName("data") val communityPosts: List<CommunityPost>
)

data class CommunityPost(
    @SerializedName("postId") val postId: Long,
    @SerializedName("content") val content: String,
    @SerializedName("challenge") val challenge: Boolean,
    @SerializedName("review") val review: Boolean,
    @SerializedName("username") val username: String,
    @SerializedName("userProfileImg") val userProfileImg: String?,
    @SerializedName("likeCount") val likeCount: Int,
    @SerializedName("commentCount") val commentCount: Int,
    @SerializedName("liked") var liked: Boolean,
    @SerializedName("bookmarked") var bookmarked: Boolean,
    @SerializedName("imageList") val imageList: List<String>
)

data class CommunityPostDetailResponse(
    @SerializedName("postId") val postId: Long,
    @SerializedName("username") val userName: String,
    @SerializedName("userProfileImg") val userProfileImg: String?,
    @SerializedName("content") val content: String,
    @SerializedName("weeksAgo") val weeksAgo: Int,
    @SerializedName("challenge") val challenge: Boolean,
    @SerializedName("review") val review: Boolean,
    @SerializedName("likeCount") val likeCount: Int,
    @SerializedName("commentCount") val commentCount: Int,
    @SerializedName("liked") var liked: Boolean,
    @SerializedName("bookmarked") var bookmarked: Boolean,
    @SerializedName("imageList") val imageList: List<String>,
    @SerializedName("commentList") val communityPostDetailComments: List<CommunityPostDetailComment>
)

data class CommunityPostDetailComment(
    @SerializedName("userId") val userId: Long,
    @SerializedName("username") val userName: String,
    @SerializedName("userProfileImg") val userProfileImg: String?,
    @SerializedName("commentId") val commentId: Long,
    @SerializedName("content") val content: String,
    @SerializedName("weeksAgo") val weeksAgo: Int,
    @SerializedName("likeCount") val likeCount: Int,
    @SerializedName("liked") var liked: Boolean
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

data class CommunityReportRequest(
    @SerializedName("targetId") val targetId: Long,
    @SerializedName("type") val content: String
)