package com.greenfriends.zeroway.data.source.remote.mypage

import com.greenfriends.zeroway.data.api.NoticeRetrofitInterface
import com.greenfriends.zeroway.data.api.RetrofitClient
import com.greenfriends.zeroway.data.model.*
import retrofit2.Response

class NoticeDataSourceImpl : NoticeDataSource {

    private val noticeService =
        RetrofitClient.getRetrofit()?.create(NoticeRetrofitInterface::class.java)

    override suspend fun getNotice(): Response<List<NoticeResponse>> {
        return noticeService!!.getNotice()
    }

    override suspend fun getNoticeDetail(announceId: Long): Response<NoticeDetailResponse> {
        return noticeService!!.getNoticeDetail(announceId)
    }
}