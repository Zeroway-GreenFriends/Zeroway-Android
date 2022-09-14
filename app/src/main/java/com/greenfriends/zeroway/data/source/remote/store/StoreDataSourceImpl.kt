package com.greenfriends.zeroway.data.source.remote.store

import com.greenfriends.zeroway.data.api.RetrofitClient
import com.greenfriends.zeroway.data.api.StoreRetrofitInterface
import com.greenfriends.zeroway.data.model.StoreResponse
import retrofit2.Response

class StoreDataSourceImpl : StoreDataSource {

    private val storeService =
        RetrofitClient.getRetrofit()?.create(StoreRetrofitInterface::class.java)

    override suspend fun getStores(
        keyword: String?,
        page: Int,
        size: Int
    ): Response<List<StoreResponse>> {
        return storeService!!.getStores(keyword, page, size)
    }
}