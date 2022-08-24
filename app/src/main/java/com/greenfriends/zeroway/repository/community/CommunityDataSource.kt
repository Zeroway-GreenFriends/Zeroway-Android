package com.greenfriends.zeroway.repository.community

import com.greenfriends.zeroway.model.CommunityPostBookmarkRequest
import com.greenfriends.zeroway.model.CommunityLikeRequest
import com.greenfriends.zeroway.model.CommunityResponse
import retrofit2.Response

interface CommunityDataSource {

    suspend fun getPosts(accessToken: String, sort: String): Response<CommunityResponse>

    suspend fun setPostLike(
        accessToken: String,
        postId: String,
        like: CommunityLikeRequest
    ): Response<Void>

    suspend fun setPostBookmark(
        accessToken: String,
        postId: String,
        bookmark: CommunityPostBookmarkRequest
    ): Response<Void>

    suspend fun deletePost(
        accessToken: String,
        postId: String
    ): Response<Void>
}