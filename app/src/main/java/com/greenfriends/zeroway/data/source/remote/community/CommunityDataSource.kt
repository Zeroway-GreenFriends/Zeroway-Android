package com.greenfriends.zeroway.data.source.remote.community

import com.greenfriends.zeroway.data.model.CommunityLikeRequest
import com.greenfriends.zeroway.data.model.CommunityPostBookmarkRequest
import com.greenfriends.zeroway.data.model.CommunityResponse
import retrofit2.Response

interface CommunityDataSource {

    suspend fun getPosts(
        accessToken: String,
        sort: String?,
        page: Long?,
        size: Long?,
        challenge: Boolean?,
        review: Boolean?
    ): Response<CommunityResponse>

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
}