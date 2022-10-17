package com.greenfriends.zeroway.data.repository.mypage

import com.greenfriends.zeroway.data.model.*
import com.greenfriends.zeroway.data.source.remote.mypage.MyPageDataSourceImpl
import retrofit2.Response

class MyPageRepository(private val noticeDataSourceImpl: MyPageDataSourceImpl) {

    suspend fun getNotice(
    ): Response<List<NoticeResponse>> {
        return noticeDataSourceImpl.getNotice()
    }

    suspend fun getNoticeDetail(announceId: Long): Response<NoticeDetailResponse> {
        return noticeDataSourceImpl.getNoticeDetail(announceId)
    }

    suspend fun getMyPost(
        accessToken: String,
        page: Long?,
        size: Long?
    ): Response<MyPostResponse> {
        return noticeDataSourceImpl.getMyPost(accessToken, page, size)
    }

    suspend fun getMyComment(
        accessToken: String,
        page: Long?,
        size: Long?
    ): Response<MyPostResponse> {
        return noticeDataSourceImpl.getMyComment(accessToken, page, size)
    }

    suspend fun getMyScrap(
        accessToken: String,
        page: Long?,
        size: Long?
    ): Response<MyPostResponse> {
        return noticeDataSourceImpl.getMyScrap(accessToken, page, size)
    }

    suspend fun getMyLike(
        accessToken: String,
        page: Long?,
        size: Long?
    ): Response<MyPostResponse> {
        return noticeDataSourceImpl.getMyLike(accessToken, page, size)
    }
}