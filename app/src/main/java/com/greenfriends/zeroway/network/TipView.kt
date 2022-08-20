package com.greenfriends.zeroway.network

import com.greenfriends.zeroway.model.TipResponse

interface TipView {
    fun onTipSuccess(result: List<TipResponse>)
    fun onTipFailure()
}