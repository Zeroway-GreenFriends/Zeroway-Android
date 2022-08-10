package com.greenfriends.zeroway.api

import com.greenfriends.zeroway.data.TipResponse
import retrofit2.Call
import retrofit2.http.*

interface HomeRetrofitInterface {
    @GET("tip")
    fun getTip(): Call<List<TipResponse>>
}