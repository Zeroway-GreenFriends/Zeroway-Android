package com.greenfriends.zeroway.repository.community

import com.greenfriends.zeroway.model.CommunityResponse
import com.greenfriends.zeroway.network.CommunityRetrofitInterface
import com.greenfriends.zeroway.network.RetrofitClient
import retrofit2.Response

class CommunityDataSourceImpl : CommunityDataSource {

    private val communityService =
        RetrofitClient.getRetrofit()?.create(CommunityRetrofitInterface::class.java)

    override suspend fun getPosts(accessToken: String, sort: String): Response<CommunityResponse> {
        return communityService!!.getPosts(accessToken, sort)
    }
}