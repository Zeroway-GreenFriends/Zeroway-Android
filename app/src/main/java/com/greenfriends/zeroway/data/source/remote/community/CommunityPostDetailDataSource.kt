package com.greenfriends.zeroway.data.source.remote.community

import com.greenfriends.zeroway.data.model.CommunityLikeRequest
import com.greenfriends.zeroway.data.model.CommunityPostBookmarkRequest
import com.greenfriends.zeroway.data.model.CommunityPostCommentRequest
import com.greenfriends.zeroway.data.model.CommunityPostDetailResponse
import retrofit2.Response

interface CommunityPostDetailDataSource {

    suspend fun getPostDetail(
        accessToken: String,
        postId: String
    ): Response<CommunityPostDetailResponse>

    suspend fun setPostComment(
        accessToken: String,
        postId: String,
        content: CommunityPostCommentRequest
    ): Response<Void>

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

    suspend fun setPostCommentLike(
        accessToken: String,
        commentId: String,
        like: CommunityLikeRequest
    ): Response<Void>
}