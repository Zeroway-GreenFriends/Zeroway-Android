package com.greenfriends.zeroway.repository.community

import okhttp3.MultipartBody
import okhttp3.RequestBody

interface CommunityPostRegisterDataSource {

    suspend fun setPost(accessToken: String, images: List<MultipartBody.Part>, post: RequestBody)
}