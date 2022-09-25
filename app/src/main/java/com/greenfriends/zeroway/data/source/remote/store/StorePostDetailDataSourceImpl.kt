package com.greenfriends.zeroway.data.source.remote.store

import com.greenfriends.zeroway.data.api.RetrofitClient
import com.greenfriends.zeroway.data.api.StoreRetrofitInterface
import com.greenfriends.zeroway.data.model.StorePostDetailResponse
import retrofit2.Response

class StorePostDetailDataSourceImpl : StorePostDetailDataSource {

    private val storeService =
        RetrofitClient.getRetrofit()?.create(StoreRetrofitInterface::class.java)

    override suspend fun getStoreDetail(storeId: String): Response<StorePostDetailResponse> {
        return storeService!!.getStoreDetail(storeId)
    }
}