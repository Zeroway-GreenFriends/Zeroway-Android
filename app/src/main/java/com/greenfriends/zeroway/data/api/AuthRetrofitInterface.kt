package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.AuthResponse
import com.greenfriends.zeroway.data.model.IdCheckResponse
import com.greenfriends.zeroway.data.model.LoginRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface AuthRetrofitInterface {

    @POST("user/auth/login")
    fun login(
        @Body loginRequest: LoginRequest
    ): Call<AuthResponse>

    /**
     * 회원가입 API
     */
    @Multipart
    @POST("user")
    suspend fun signUp(
        @Part profileImg: MultipartBody.Part?,
        @Part("signInReq") user: RequestBody
    ): Response<AuthResponse>

    /**
     * 닉네임 중복 확인 API
     */
    @GET("user/{nickname}")
    suspend fun idCheck(
        @Path("nickname") nickname: String
    ): Response<IdCheckResponse>
}