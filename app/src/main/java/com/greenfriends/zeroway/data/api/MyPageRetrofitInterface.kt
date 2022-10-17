package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.MyPostResponse
import com.greenfriends.zeroway.data.model.NoticeDetailResponse
import com.greenfriends.zeroway.data.model.NoticeResponse
import com.greenfriends.zeroway.data.model.TermResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MyPageRetrofitInterface {

    /**
     * 공지사항 전체 조회
     */
    @GET("announce")
    suspend fun getNotice(
    ): Response<List<NoticeResponse>>

    /**
     * 공지사항 상세 조회
     */
    @GET("announce/{announceId}")
    suspend fun getNoticeDetail(
        @Path("announceId") announceId: Long
    ): Response<NoticeDetailResponse>


    @GET("post/user")
    suspend fun getMyPost(
        @Header("Bearer") accessToken: String,
        @Query("page") page: Long? = null,
        @Query("size") size: Long? = null
    ): Response<MyPostResponse>

}