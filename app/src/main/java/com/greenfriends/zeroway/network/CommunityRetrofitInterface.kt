package com.greenfriends.zeroway.network

import com.greenfriends.zeroway.model.*
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
     * 커뮤니티 게시물 전체 조회 API
     */
    @GET("post/list")
    suspend fun getPosts(
        @Header("Bearer") accessToken: String,
        @Query("sort") sort: String
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
     * 커뮤니티 게시물 댓글 작성 API
     */
    @POST("post/{postId}/comment")
    suspend fun setPostComment(
        @Header("Bearer") accessToken: String,
        @Path("postId") postId: String,
        @Body content: CommunityPostCommentRequest
    ): Response<Void>

    /**
     * 커뮤니티 게시물 좋아요 API
     */
    @POST("post/{postId}/like")
    suspend fun setPostLike(
        @Header("Bearer") accessToken: String,
        @Path("postId") postId: String,
        @Body like: CommunityPostLikeRequest
    ): Response<Void>
}