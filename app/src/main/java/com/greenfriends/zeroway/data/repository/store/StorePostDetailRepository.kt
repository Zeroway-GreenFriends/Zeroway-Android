package com.greenfriends.zeroway.data.repository.store

import com.greenfriends.zeroway.data.model.StorePostDetailResponse
import com.greenfriends.zeroway.data.source.remote.store.StorePostDetailDataSourceImpl
import retrofit2.Response

class StorePostDetailRepository(private val storePostDetailDataSource: StorePostDetailDataSourceImpl) {

    suspend fun getStoreDetail(storeId: String): Response<StorePostDetailResponse> {
        return storePostDetailDataSource.getStoreDetail(storeId)
    }
}