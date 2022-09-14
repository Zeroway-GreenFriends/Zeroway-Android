package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.TermResponse
import com.greenfriends.zeroway.data.model.TipResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface HomeRetrofitInterface {
    @GET("tip")
    fun getTip(): Response<List<TipResponse>>

    @GET("term")
    fun getTerm(
        @Query("keyword") keyword: String? = null,
        @Query("keyword") page: Int? = null,
        @Query("keyword") size: Int? = null,
    ): Response<List<TermResponse>>
}