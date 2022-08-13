package com.greenfriends.zeroway.repository.community

import okhttp3.MultipartBody
import okhttp3.RequestBody

class CommunityPostRegisterRepository(private val communityPostDataSourceImpl: CommunityPostRegisterDataSourceImpl) {

    suspend fun setPost(accessToken: String, images: MultipartBody.Part, post: RequestBody) {
        communityPostDataSourceImpl.setPost(accessToken, images, post)
    }
}