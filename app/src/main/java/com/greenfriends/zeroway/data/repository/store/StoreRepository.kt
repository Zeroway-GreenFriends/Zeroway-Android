package com.greenfriends.zeroway.data.repository.store

import com.greenfriends.zeroway.data.model.StoreResponse
import com.greenfriends.zeroway.data.source.remote.store.StoreDataSourceImpl
import retrofit2.Response

class StoreRepository(private val storeDataSourceImpl: StoreDataSourceImpl) {

    suspend fun getStores(keyword: String?, page: Int, size: Int): Response<List<StoreResponse>> {
        return storeDataSourceImpl.getStores(keyword, page, size)
    }
}