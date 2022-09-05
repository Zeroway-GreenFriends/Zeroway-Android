package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.StoreResponse


interface StoreListView {
    fun onStoreListSuccess(result: List<StoreResponse>)
    fun onStoreListFailure()
}