package com.greenfriends.zeroway.api

import com.greenfriends.zeroway.data.AuthResponse
import com.greenfriends.zeroway.data.LoginRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AuthRetrofitInterface {
    @POST("user/auth/login")
    fun login(
        @Body loginRequest: LoginRequest
    ): Call<AuthResponse>

    @Multipart
    @POST("user")
    fun signUp(
        @Part profileImg: MultipartBody.Part,
        @Part("signInReq") user: RequestBody
    ): Call<AuthResponse>
}