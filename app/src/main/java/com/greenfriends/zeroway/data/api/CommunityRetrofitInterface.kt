package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface CommunityRetrofitInterface {

    /**
     * 커뮤니티 게시물 작성 API
     */
    @Multipart
    @POST("post")
    suspend fun setPost(
        @Header("Bearer") accessToken: String,
        @Part images: List<MultipartBody.Part>,
        @Part("post") post: RequestBody
    ): Response<Void>

    /**
     * 커뮤니티 게시물 조회 API
     */
    @GET("post/list")
    suspend fun getPosts(
        @Header("Bearer") accessToken: String,
        @Query("sort") sort: String?,
        @Query("page") page: Long?,
        @Query("size") size: Long?,
        @Query("challenge") challenge: Boolean?,
        @Query("review") review: Boolean?
    ): Response<CommunityResponse>

    /**
     * 커뮤니티 게시물 상세 조회 API
     */
    @GET("post/{postId}")
    suspend fun getPostDetail(
        @Header("Bearer") accessToken: String,
        @Path("postId") postId: String
    ): Response<CommunityPostDetailResponse>

    /**
     * 커뮤니티 게시물 좋아요 API
     */
    @POST("post/{postId}/like")
    suspend fun setPostLike(
        @Header("Bearer") accessToken: String,
        @Path("postId") postId: String,
        @Body like: CommunityLikeRequest
    ): Response<Void>

    /**
     * 커뮤니티 게시물 북마크 API
     */
    @POST("post/{postId}/bookmark")
    suspend fun setPostBookmark(
        @Header("Bearer") accessToken: String,
        @Path("postId") postId: String,
        @Body bookmark: CommunityPostBookmarkRequest
    ): Response<Void>

    /**
     * 커뮤니티 게시물 삭제 API
     */
    @PATCH("post/{postId}/delete")
    suspend fun deletePost(
        @Header("Bearer") accessToken: String,
        @Path("postId") postId: String
    ): Response<Void>

    /**
     * 커뮤니티 게시물 댓글 작성 API
     */
    @POST("post/{postId}/comment")
    suspend fun setPostComment(
        @Header("Bearer") accessToken: String,
        @Path("postId") postId: String,
        @Body content: CommunityPostCommentRequest
    ): Response<Void>

    /**
     * 커뮤니티 게시물 댓글 좋아요 API
     */
    @POST("comment/{commentId}/like")
    suspend fun setPostCommentLike(
        @Header("Bearer") accessToken: String,
        @Path("commentId") commentId: String,
        @Body like: CommunityLikeRequest
    ): Response<Void>

    /**
     * 커뮤니티 게시물 댓글 삭제 API
     */
    @PATCH("comment/{commentId}/delete")
    suspend fun deletePostComment(
        @Header("Bearer") accessToken: String,
        @Path("commentId") commentId: String
    ): Response<Void>

    /**
     * 커뮤니티 게시물 신고 API
     */
    @POST("post/report")
    suspend fun reportPost(
        @Header("Bearer") accessToken: String,
        @Body reportReq: CommunityReportRequest
    ): Response<Void>

    /**
     * 커뮤니티 게시물 댓글 신고 API
     */
    @POST("comment/report")
    suspend fun reportPostComment(
        @Header("Bearer") accessToken: String,
        @Body reportReq: CommunityReportRequest
    ): Response<Void>
}