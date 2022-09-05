package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.TermResponse


interface TermSearchView {
    fun onTermSearchSuccess(result: List<TermResponse>)
    fun onTermSearchFailure()
}