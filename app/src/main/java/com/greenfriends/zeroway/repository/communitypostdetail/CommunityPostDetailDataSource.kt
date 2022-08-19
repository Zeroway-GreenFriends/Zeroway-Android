package com.greenfriends.zeroway.repository.communitypostdetail

import com.greenfriends.zeroway.model.CommunityPostDetailResponse
import retrofit2.Response

interface CommunityPostDetailDataSource {

    suspend fun getPostDetail(accessToken: String, postId: String): Response<CommunityPostDetailResponse>
}