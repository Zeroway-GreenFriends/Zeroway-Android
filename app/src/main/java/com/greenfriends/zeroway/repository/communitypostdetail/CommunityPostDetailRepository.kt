package com.greenfriends.zeroway.repository.communitypostdetail

import com.greenfriends.zeroway.model.CommunityPostBookmarkRequest
import com.greenfriends.zeroway.model.CommunityPostCommentRequest
import com.greenfriends.zeroway.model.CommunityPostDetailResponse
import com.greenfriends.zeroway.model.CommunityPostLikeRequest
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

    suspend fun setPostLike(
        accessToken: String,
        postId: String,
        like: CommunityPostLikeRequest
    ): Response<Void> {
        return communityPostDetailDataSourceImpl.setPostLike(accessToken, postId, like)
    }

    suspend fun setPostBookmark(
        accessToken: String,
        postId: String,
        bookmark: CommunityPostBookmarkRequest
    ): Response<Void> {
        return communityPostDetailDataSourceImpl.setPostBookmark(accessToken, postId, bookmark)
    }

    suspend fun deletePost(accessToken: String, postId: String): Response<Void> {
        return communityPostDetailDataSourceImpl.deletePost(accessToken, postId)
    }
}