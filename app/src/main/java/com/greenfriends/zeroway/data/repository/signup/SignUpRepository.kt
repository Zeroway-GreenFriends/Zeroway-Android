package com.greenfriends.zeroway.data.repository.signup

import com.greenfriends.zeroway.data.model.AuthResponse
import com.greenfriends.zeroway.data.model.IdCheckResponse
import com.greenfriends.zeroway.data.source.remote.signup.SignUpDataSourceImpl
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class SignUpRepository(private val signUpDataSourceImpl: SignUpDataSourceImpl) {

    suspend fun signUp(profileImg: MultipartBody.Part?, user: RequestBody): Response<AuthResponse> {
        return signUpDataSourceImpl.signUp(profileImg, user)
    }

    suspend fun idCheck(nickname: String): Response<IdCheckResponse> {
        return signUpDataSourceImpl.idCheck(nickname)
    }
}