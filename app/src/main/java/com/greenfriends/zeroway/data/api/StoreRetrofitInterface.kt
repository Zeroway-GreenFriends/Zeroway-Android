package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.StoreResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StoreRetrofitInterface {

    @GET("shop/list")
    fun getStoreList(
        @Query("keyword") keyword: String? = null,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
    ): Call<List<StoreResponse>>

    /**
     * 제로 웨이스트 샵 조회 API
     */
    @GET("shop/list")
    suspend fun getStores(
        @Query("keyword") keyword: String?,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<List<StoreResponse>>
}