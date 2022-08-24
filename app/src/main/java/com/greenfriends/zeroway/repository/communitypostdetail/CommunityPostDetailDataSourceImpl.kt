package com.greenfriends.zeroway.repository.communitypostdetail

import com.greenfriends.zeroway.model.CommunityLikeRequest
import com.greenfriends.zeroway.model.CommunityPostBookmarkRequest
import com.greenfriends.zeroway.model.CommunityPostCommentRequest
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

    override suspend fun setPostCommentLike(
        accessToken: String,
        commentId: String,
        like: CommunityLikeRequest
    ): Response<Void> {
        return communityService!!.setPostCommentLike(accessToken, commentId, like)
    }
}