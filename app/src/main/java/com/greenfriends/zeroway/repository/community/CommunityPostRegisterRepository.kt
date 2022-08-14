package com.greenfriends.zeroway.repository.community

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class CommunityPostRegisterRepository(private val communityPostDataSourceImpl: CommunityPostRegisterDataSourceImpl) {

    suspend fun setPost(
        accessToken: String,
        images: List<MultipartBody.Part>,
        post: RequestBody
    ): Response<Void> {
        return communityPostDataSourceImpl.setPost(accessToken, images, post)
    }
}