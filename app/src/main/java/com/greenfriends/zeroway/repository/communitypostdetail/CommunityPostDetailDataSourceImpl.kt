package com.greenfriends.zeroway.repository.communitypostdetail

import com.greenfriends.zeroway.model.CommunityPostDetailResponse
import com.greenfriends.zeroway.network.CommunityRetrofitInterface
import com.greenfriends.zeroway.network.RetrofitClient
import retrofit2.Response

class CommunityPostDetailDataSourceImpl : CommunityPostDetailDataSource {

    private val communityService =
        RetrofitClient.getRetrofit()?.create(CommunityRetrofitInterface::class.java)

    override suspend fun getPostDetail(
        accessToken: String,
        postId: String
    ): Response<CommunityPostDetailResponse> {
        return communityService!!.getPostDetail(accessToken, postId)
    }
}