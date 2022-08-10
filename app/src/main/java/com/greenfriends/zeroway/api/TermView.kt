package com.greenfriends.zeroway.api

import com.greenfriends.zeroway.data.TermResponse


interface TermView {
    fun onTermSuccess(result: List<TermResponse>)
    fun onTermFailure()
}