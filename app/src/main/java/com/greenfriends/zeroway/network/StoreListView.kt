package com.greenfriends.zeroway.network

import com.greenfriends.zeroway.model.StoreResponse
import com.greenfriends.zeroway.model.TermResponse


interface StoreListView {
    fun onStoreListSuccess(result: List<StoreResponse>)
    fun onStoreListFailure()
}