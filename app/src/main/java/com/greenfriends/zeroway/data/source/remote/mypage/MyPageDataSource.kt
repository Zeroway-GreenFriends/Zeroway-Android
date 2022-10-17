package com.greenfriends.zeroway.data.source.remote.mypage

import com.greenfriends.zeroway.data.model.*
import retrofit2.Response

interface MyPageDataSource {

    suspend fun getNotice(): Response<List<NoticeResponse>>

    suspend fun getNoticeDetail(announceId: Long): Response<NoticeDetailResponse>

    suspend fun getMyPost(
        accessToken: String,
        page: Long?,
        size: Long?
    ): Response<MyPostResponse>
}