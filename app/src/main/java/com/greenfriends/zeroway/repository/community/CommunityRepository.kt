package com.greenfriends.zeroway.repository.community

import com.greenfriends.zeroway.model.CommunityResponse
import retrofit2.Response

class CommunityRepository(private val communityDataSourceImpl: CommunityDataSourceImpl) {

    suspend fun getPosts(accessToken: String, sort: String): Response<CommunityResponse> {
        return communityDataSourceImpl.getPosts(accessToken, sort)
    }
}