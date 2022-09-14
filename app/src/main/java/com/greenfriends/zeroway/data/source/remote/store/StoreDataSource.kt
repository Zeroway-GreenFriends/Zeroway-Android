package com.greenfriends.zeroway.data.source.remote.store

import com.greenfriends.zeroway.data.model.StoreResponse
import retrofit2.Response

interface StoreDataSource {

    suspend fun getStores(keyword: String?, page: Int, size: Int): Response<List<StoreResponse>>
}