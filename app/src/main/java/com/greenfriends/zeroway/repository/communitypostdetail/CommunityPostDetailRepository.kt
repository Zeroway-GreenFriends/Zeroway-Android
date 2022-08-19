package com.greenfriends.zeroway.repository.communitypostdetail

import com.greenfriends.zeroway.model.CommunityPostDetailResponse
import com.greenfriends.zeroway.repository.communitypostdetail.CommunityPostDetailDataSourceImpl
import retrofit2.Response

class CommunityPostDetailRepository(private val communityPostDetailDataSourceImpl: CommunityPostDetailDataSourceImpl) {

    suspend fun getPostDetail(
        accessToken: String,
        postId: String
    ): Response<CommunityPostDetailResponse> {
        return communityPostDetailDataSourceImpl.getPostDetail(accessToken, postId)
    }
}