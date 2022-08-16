package com.greenfriends.zeroway.api

import com.greenfriends.zeroway.model.TermResponse


interface TermView {
    fun onTermSuccess(result: List<TermResponse>)
    fun onTermFailure()
}