package com.greenfriends.zeroway.data.source.remote.store

import com.greenfriends.zeroway.data.model.StorePostDetailResponse
import retrofit2.Response

interface StorePostDetailDataSource {

    suspend fun getStoreDetail(storeId: String): Response<StorePostDetailResponse>
}