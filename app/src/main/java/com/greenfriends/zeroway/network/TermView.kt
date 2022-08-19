package com.greenfriends.zeroway.network

import com.greenfriends.zeroway.model.TermResponse


interface TermView {
    fun onTermSuccess(result: List<TermResponse>)
    fun onTermFailure()
}