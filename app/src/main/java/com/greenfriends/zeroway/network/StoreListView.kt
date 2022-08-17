package com.greenfriends.zeroway.api

import com.greenfriends.zeroway.model.StoreResponse
import com.greenfriends.zeroway.model.TermResponse


interface StoreListView {
    fun onStoreListSuccess(result: List<StoreResponse>)
    fun onStoreListFailure()
}