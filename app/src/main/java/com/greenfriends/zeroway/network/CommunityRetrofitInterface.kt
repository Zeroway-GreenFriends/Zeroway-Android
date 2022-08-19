package com.greenfriends.zeroway.network

import com.greenfriends.zeroway.model.CommunityPostDetailResponse
import com.greenfriends.zeroway.model.CommunityResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface CommunityRetrofitInterface {

    // 게시물 작성 API
    @Multipart
    @POST("post")
    suspend fun setPost(
        @Header("Bearer") accessToken: String,
        @Part images: List<MultipartBody.Part>,
        @Part("post") post: RequestBody
    ): Response<Void>

    // 게시물 전체 조회 API
    @GET("post/list")
    suspend fun getPosts(
        @Header("Bearer") accessToken: String,
        @Query("sort") sort: String
    ): Response<CommunityResponse>

    // 게시물 상세 조회 API
    @GET("post/{postId}")
    suspend fun getPostDetail(
        @Header("Bearer") accessToken: String,
        @Path("postId") postId: String
    ): Response<CommunityPostDetailResponse>
}