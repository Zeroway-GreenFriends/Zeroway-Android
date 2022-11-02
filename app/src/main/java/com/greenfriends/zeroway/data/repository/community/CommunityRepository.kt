package com.greenfriends.zeroway.data.repository.community

import com.greenfriends.zeroway.data.model.CommunityLikeRequest
import com.greenfriends.zeroway.data.model.CommunityPostBookmarkRequest
import com.greenfriends.zeroway.data.model.CommunityReportRequest
import com.greenfriends.zeroway.data.model.CommunityResponse
import com.greenfriends.zeroway.data.source.remote.community.CommunityDataSourceImpl
import retrofit2.Response

class CommunityRepository(private val communityDataSourceImpl: CommunityDataSourceImpl) {

    suspend fun getPosts(
        accessToken: String,
        sort: String?,
        page: Long?,
        size: Long?,
        challenge: Boolean?,
        review: Boolean?
    ): Response<CommunityResponse> {
        return communityDataSourceImpl.getPosts(accessToken, sort, page, size, challenge, review)
    }

    suspend fun setPostLike(
        accessToken: String,
        postId: String,
        like: CommunityLikeRequest
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

    suspend fun reportPost(
        accessToken: String,
        reportReq: CommunityReportRequest
    ): Response<Void> {
        return communityDataSourceImpl.reportPost(accessToken, reportReq)
    }
}