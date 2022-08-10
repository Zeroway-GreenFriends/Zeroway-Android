package com.greenfriends.zeroway.api

import com.greenfriends.zeroway.data.AuthResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface AuthRetrofitInterface {
    @POST("user/auth/login")
    fun login(
        @Body email: String
    ): Call<AuthResponse>

    @Multipart
    @POST("user")
    fun signUp(
        @Part profileImg: MultipartBody.Part,
        @Part("signInReq") user: RequestBody
    ): Call<AuthResponse>
}