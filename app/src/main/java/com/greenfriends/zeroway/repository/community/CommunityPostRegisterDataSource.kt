package com.greenfriends.zeroway.repository.community

import okhttp3.MultipartBody
import okhttp3.RequestBody

interface CommunityPostRegisterDataSource {

    suspend fun setPost(accessToken: String, images: MultipartBody.Part, post: RequestBody)
}