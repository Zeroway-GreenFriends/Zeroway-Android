package com.greenfriends.zeroway.data.repository.community

import com.greenfriends.zeroway.data.model.*
import com.greenfriends.zeroway.data.source.remote.community.CommunityPostDetailDataSourceImpl
import retrofit2.Response

class CommunityPostDetailRepository(private val communityPostDetailDataSourceImpl: CommunityPostDetailDataSourceImpl) {

    suspend fun getPostDetail(
        accessToken: String,
        postId: String
    ): Response<CommunityPostDetailResponse> {
        return communityPostDetailDataSourceImpl.getPostDetail(accessToken, postId)
    }

    suspend fun setPostLike(
        accessToken: String,
        postId: String,
        like: CommunityLikeRequest
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

    suspend fun setPostCommentLike(
        accessToken: String,
        commentId: String,
        like: CommunityLikeRequest
    ): Response<Void> {
        return communityPostDetailDataSourceImpl.setPostCommentLike(accessToken, commentId, like)
    }

    suspend fun setPostComment(
        accessToken: String,
        postId: String,
        content: CommunityPostCommentRequest
    ): Response<Void> {
        return communityPostDetailDataSourceImpl.setPostComment(accessToken, postId, content)
    }

    suspend fun deletePostComment(accessToken: String, commentId: String): Response<Void> {
        return communityPostDetailDataSourceImpl.deletePostComment(accessToken, commentId)
    }

    suspend fun reportPost(
        accessToken: String,
        reportReq: CommunityReportRequest
    ): Response<Void> {
        return communityPostDetailDataSourceImpl.reportPost(accessToken, reportReq)
    }
}