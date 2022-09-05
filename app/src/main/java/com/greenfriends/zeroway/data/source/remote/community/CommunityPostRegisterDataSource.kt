package com.greenfriends.zeroway.data.source.remote.community

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface CommunityPostRegisterDataSource {

    suspend fun setPost(
        accessToken: String,
        images: List<MultipartBody.Part>,
        post: RequestBody
    ): Response<Void>
}