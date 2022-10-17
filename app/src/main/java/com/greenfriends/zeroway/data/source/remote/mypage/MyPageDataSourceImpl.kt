package com.greenfriends.zeroway.data.source.remote.mypage

import com.greenfriends.zeroway.data.api.MyPageRetrofitInterface
import com.greenfriends.zeroway.data.api.RetrofitClient
import com.greenfriends.zeroway.data.model.*
import retrofit2.Response

class MyPageDataSourceImpl : MyPageDataSource {

    private val noticeService =
        RetrofitClient.getRetrofit()?.create(MyPageRetrofitInterface::class.java)

    override suspend fun getNotice(): Response<List<NoticeResponse>> {
        return noticeService!!.getNotice()
    }

    override suspend fun getNoticeDetail(announceId: Long): Response<NoticeDetailResponse> {
        return noticeService!!.getNoticeDetail(announceId)
    }

    override suspend fun getMyPost(
        accessToken: String,
        page: Long?,
        size: Long?
    ): Response<MyPostResponse> {
        return noticeService!!.getMyPost(accessToken, page, size)
    }
}