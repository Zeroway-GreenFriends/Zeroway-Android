package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.StoreResponse
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