package com.greenfriends.zeroway.data.source.remote.signup

import com.greenfriends.zeroway.data.model.AuthResponse
import com.greenfriends.zeroway.data.model.IdCheckResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface SignUpDataSource {

    suspend fun signUp(profileImg: MultipartBody.Part, user: RequestBody): Response<AuthResponse>

    suspend fun idCheck(nickname: String): Response<IdCheckResponse>
}