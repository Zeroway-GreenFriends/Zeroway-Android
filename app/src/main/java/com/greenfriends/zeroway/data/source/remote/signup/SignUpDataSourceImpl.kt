package com.greenfriends.zeroway.data.source.remote.signup

import com.greenfriends.zeroway.data.api.AuthRetrofitInterface
import com.greenfriends.zeroway.data.api.RetrofitClient
import com.greenfriends.zeroway.data.model.AuthResponse
import com.greenfriends.zeroway.data.model.IdCheckResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class SignUpDataSourceImpl : SignUpDataSource {

    private val authService =
        RetrofitClient.getRetrofit()?.create(AuthRetrofitInterface::class.java)

    override suspend fun signUp(
        profileImg: MultipartBody.Part?,
        user: RequestBody
    ): Response<AuthResponse> {
        return authService!!.signUp(profileImg, user)
    }

    override suspend fun idCheck(nickname: String): Response<IdCheckResponse> {
        return authService!!.idCheck(nickname)
    }
}