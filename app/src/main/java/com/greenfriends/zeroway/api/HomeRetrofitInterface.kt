package com.greenfriends.zeroway.api

import com.greenfriends.zeroway.data.TermResponse
import com.greenfriends.zeroway.data.TipResponse
import retrofit2.Call
import retrofit2.http.*

interface HomeRetrofitInterface {
    @GET("tip")
    fun getTip(): Call<List<TipResponse>>

    @GET("term")
    fun getTerm(
        @Query("keyword") keyword: String? = null,
        @Query("keyword") page: Int? = null,
        @Query("keyword") size: Int? = null,
    ): Call<List<TermResponse>>
}