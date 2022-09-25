package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.StorePostDetailResponse
import com.greenfriends.zeroway.data.model.StoreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StoreRetrofitInterface {

    /**
     * 제로 웨이스트 샵 조회 API
     */
    @GET("shop/list")
    suspend fun getStores(
        @Query("keyword") keyword: String?,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<List<StoreResponse>>

    /**
     * 제로 웨이스트 샵 상세 조회 API
     */
    @GET("shop/{storeId}")
    suspend fun getStoreDetail(
        @Path("storeId") storeId: String
    ): Response<StorePostDetailResponse>
}