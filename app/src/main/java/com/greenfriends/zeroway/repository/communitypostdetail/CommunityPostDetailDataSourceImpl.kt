package com.greenfriends.zeroway.repository.communitypostdetail

import com.greenfriends.zeroway.model.CommunityPostCommentRequest
import com.greenfriends.zeroway.model.CommunityPostDetailResponse
import com.greenfriends.zeroway.model.CommunityPostLikeRequest
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

    override suspend fun setPostComment(
        accessToken: String,
        postId: String,
        content: CommunityPostCommentRequest
    ): Response<Void> {
        return communityService!!.setPostComment(accessToken, postId, content)
    }

    override suspend fun setPostLike(
        accessToken: String,
        postId: String,
        like: CommunityPostLikeRequest
    ): Response<Void> {
        return communityService!!.setPostLike(accessToken, postId, like)
    }
}