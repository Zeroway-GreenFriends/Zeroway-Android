package com.greenfriends.zeroway.repository.community

import com.greenfriends.zeroway.model.CommunityPostLikeRequest
import com.greenfriends.zeroway.model.CommunityResponse
import retrofit2.Response

interface CommunityDataSource {

    suspend fun getPosts(accessToken: String, sort: String): Response<CommunityResponse>

    suspend fun setPostLike(
        accessToken: String,
        postId: String,
        like: CommunityPostLikeRequest
    ): Response<Void>
}