package com.greenfriends.zeroway.api

import android.util.Log
import com.greenfriends.zeroway.data.AuthResponse
import com.greenfriends.zeroway.data.TipResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService {
    private var retrofit = RetrofitClient.getRetrofit()
    private lateinit var tipView: TipView

    fun setTipView(tipView: TipView) {
        this.tipView = tipView
    }

    fun getTip() {
        val homeService = retrofit?.create(HomeRetrofitInterface::class.java)
        homeService!!.getTip().enqueue(object : Callback<List<TipResponse>> {
            override fun onResponse(call: Call<List<TipResponse>>, response: Response<List<TipResponse>>) {
                Log.d("TIP/SUCCESS", response.body().toString())
                if (response.isSuccessful) {
                    tipView.onTipSuccess(response.body()!!)
                } else {
                    tipView.onTipFailure()
                }
            }

            override fun onFailure(call: Call<List<TipResponse>>, t: Throwable) {
                Log.d("TIP/FAILURE", t.message.toString())
            }
        })
    }

}