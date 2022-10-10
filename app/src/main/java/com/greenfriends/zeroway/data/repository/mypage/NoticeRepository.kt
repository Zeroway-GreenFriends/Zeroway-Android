package com.greenfriends.zeroway.data.repository.mypage

import com.greenfriends.zeroway.data.model.*
import com.greenfriends.zeroway.data.source.remote.mypage.NoticeDataSourceImpl
import retrofit2.Response

class NoticeRepository(private val noticeDataSourceImpl: NoticeDataSourceImpl) {

    suspend fun getNotice(
    ): Response<List<NoticeResponse>> {
        return noticeDataSourceImpl.getNotice()
    }

    suspend fun getNoticeDetail(announceId: Long): Response<NoticeDetailResponse> {
        return noticeDataSourceImpl.getNoticeDetail(announceId)
    }

}