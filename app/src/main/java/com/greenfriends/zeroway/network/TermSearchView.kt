package com.greenfriends.zeroway.network

import com.greenfriends.zeroway.model.TermResponse


interface TermSearchView {
    fun onTermSearchSuccess(result: List<TermResponse>)
    fun onTermSearchFailure()
}