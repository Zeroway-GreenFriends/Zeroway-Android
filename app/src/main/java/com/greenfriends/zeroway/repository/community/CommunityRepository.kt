package com.greenfriends.zeroway.repository.community

import com.greenfriends.zeroway.model.CommunityPostBookmarkRequest
import com.greenfriends.zeroway.model.CommunityPostLikeRequest
import com.greenfriends.zeroway.model.CommunityResponse
import retrofit2.Response

class CommunityRepository(private val communityDataSourceImpl: CommunityDataSourceImpl) {

    suspend fun getPosts(accessToken: String, sort: String): Response<CommunityResponse> {
        return communityDataSourceImpl.getPosts(accessToken, sort)
    }

    suspend fun setPostLike(
        accessToken: String,
        postId: String,
        like: CommunityPostLikeRequest
    ): Response<Void> {
        return communityDataSourceImpl.setPostLike(accessToken, postId, like)
    }

    suspend fun setPostBookmark(
        accessToken: String,
        postId: String,
        bookmark: CommunityPostBookmarkRequest
    ): Response<Void> {
        return communityDataSourceImpl.setPostBookmark(accessToken, postId, bookmark)
    }
}