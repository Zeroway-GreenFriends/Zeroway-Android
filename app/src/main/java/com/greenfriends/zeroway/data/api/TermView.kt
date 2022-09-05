package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.TermResponse


interface TermView {
    fun onTermSuccess(result: List<TermResponse>)
    fun onTermFailure()
}