package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.TipResponse

interface TipView {
    fun onTipSuccess(result: List<TipResponse>)
    fun onTipFailure()
}