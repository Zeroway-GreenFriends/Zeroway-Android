package com.greenfriends.zeroway.repository.communitypostdetail

import com.greenfriends.zeroway.model.CommunityPostCommentRequest
import com.greenfriends.zeroway.model.CommunityPostDetailResponse
import com.greenfriends.zeroway.model.CommunityPostLikeRequest
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
        like: CommunityPostLikeRequest
    ): Response<Void>
}