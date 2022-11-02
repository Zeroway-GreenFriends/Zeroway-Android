package com.greenfriends.zeroway.data.source.remote.community

import com.greenfriends.zeroway.data.model.*
import retrofit2.Response

interface CommunityPostDetailDataSource {

    suspend fun getPostDetail(
        accessToken: String,
        postId: String
    ): Response<CommunityPostDetailResponse>

    suspend fun setPostLike(
        accessToken: String,
        postId: String,
        like: CommunityLikeRequest
    ): Response<Void>

    suspend fun setPostBookmark(
        accessToken: String,
        postId: String,
        bookmark: CommunityPostBookmarkRequest
    ): Response<Void>

    suspend fun deletePost(
        accessToken: String,
        postId: String
    ): Response<Void>

    suspend fun setPostComment(
        accessToken: String,
        postId: String,
        content: CommunityPostCommentRequest
    ): Response<Void>

    suspend fun setPostCommentLike(
        accessToken: String,
        commentId: String,
        like: CommunityLikeRequest
    ): Response<Void>

    suspend fun deletePostComment(
        accessToken: String,
        commentId: String
    ): Response<Void>

    suspend fun reportPost(
        accessToken: String,
        reportReq: CommunityReportRequest
    ): Response<Void>

    suspend fun reportPostComment(
        accessToken: String,
        reportReq: CommunityReportRequest
    ): Response<Void>
}