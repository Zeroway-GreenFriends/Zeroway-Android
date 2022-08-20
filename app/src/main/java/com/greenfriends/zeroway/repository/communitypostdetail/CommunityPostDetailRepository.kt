package com.greenfriends.zeroway.repository.communitypostdetail

import com.greenfriends.zeroway.model.CommunityPostCommentRequest
import com.greenfriends.zeroway.model.CommunityPostDetailResponse
import retrofit2.Response

class CommunityPostDetailRepository(private val communityPostDetailDataSourceImpl: CommunityPostDetailDataSourceImpl) {

    suspend fun getPostDetail(
        accessToken: String,
        postId: String
    ): Response<CommunityPostDetailResponse> {
        return communityPostDetailDataSourceImpl.getPostDetail(accessToken, postId)
    }

    suspend fun setPostComment(
        accessToken: String,
        postId: String,
        content: CommunityPostCommentRequest
    ): Response<Void> {
        return communityPostDetailDataSourceImpl.setPostComment(accessToken, postId, content)
    }
}