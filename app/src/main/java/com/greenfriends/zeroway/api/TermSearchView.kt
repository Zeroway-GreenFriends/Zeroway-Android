package com.greenfriends.zeroway.api

import com.greenfriends.zeroway.data.TermResponse


interface TermSearchView {
    fun onTermSearchSuccess(result: List<TermResponse>)
    fun onTermSearchFailure()
}