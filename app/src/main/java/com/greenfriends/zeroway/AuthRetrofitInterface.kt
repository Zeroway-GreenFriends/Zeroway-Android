package com.greenfriends.zeroway

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface AuthRetrofitInterface {

    @Multipart
    @POST("user/auth/login")
    fun login(
        @Part profileImg: MultipartBody.Part,
        @Part("signInReq") signInReq: RequestBody
    ): Call<LoginResponse>

}