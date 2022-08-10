package com.greenfriends.zeroway.api

import com.greenfriends.zeroway.data.TipResponse

interface TipView {
    fun onTipSuccess(result: List<TipResponse>)
    fun onTipFailure()
}