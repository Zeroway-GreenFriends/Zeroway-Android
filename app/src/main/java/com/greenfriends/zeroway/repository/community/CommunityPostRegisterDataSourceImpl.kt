package com.greenfriends.zeroway.repository.community

import com.greenfriends.zeroway.network.CommunityRetrofitInterface
import com.greenfriends.zeroway.network.RetrofitClient
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