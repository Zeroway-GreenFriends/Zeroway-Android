package com.greenfriends.zeroway.network

import com.greenfriends.zeroway.model.StoreResponse
import com.greenfriends.zeroway.model.TermResponse
import com.greenfriends.zeroway.model.TipResponse
import retrofit2.Call
import retrofit2.http.*

interface StoreRetrofitInterface {

    @GET("shop/list")
    fun getStoreList(
        @Query("keyword") keyword: String? = null,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
    ): Call<List<StoreResponse>>
}