package com.greenfriends.zeroway.data.source.remote.community

import com.greenfriends.zeroway.data.api.CommunityRetrofitInterface
import com.greenfriends.zeroway.data.api.RetrofitClient
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class CommunityPostRegisterDataSourceImpl : CommunityPostRegisterDataSource {

    private val communityService =
        RetrofitClient.getRetrofit()?.create(CommunityRetrofitInterface::class.java)

    override suspend fun setPost(
        accessToken: String,
        images: List<MultipartBody.Part>,
        post: RequestBody
    ): Response<Void> {
        return communityService!!.setPost(accessToken, images, post)
    }
}