package com.greenfriends.zeroway.repository.community

import com.greenfriends.zeroway.model.CommunityPostBookmarkRequest
import com.greenfriends.zeroway.model.CommunityLikeRequest
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

    override suspend fun setPostLike(
        accessToken: String,
        postId: String,
        like: CommunityLikeRequest
    ): Response<Void> {
        return communityService!!.setPostLike(accessToken, postId, like)
    }

    override suspend fun setPostBookmark(
        accessToken: String,
        postId: String,
        bookmark: CommunityPostBookmarkRequest
    ): Response<Void> {
        return communityService!!.setPostBookmark(accessToken, postId, bookmark)
    }

    override suspend fun deletePost(accessToken: String, postId: String): Response<Void> {
        return communityService!!.deletePost(accessToken, postId)
    }
}