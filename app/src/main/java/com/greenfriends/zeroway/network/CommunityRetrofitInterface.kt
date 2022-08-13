package com.greenfriends.zeroway.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CommunityRetrofitInterface {

    @Multipart
    @POST("post")
    suspend fun setPost(
        @Header("accessToken") accessToken: String,
        @Part images: MultipartBody.Part,
        @Part post: RequestBody
    ): Response<Void>
}