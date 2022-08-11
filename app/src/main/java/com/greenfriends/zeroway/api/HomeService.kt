package com.greenfriends.zeroway.api

import android.util.Log
import com.greenfriends.zeroway.data.TermResponse
import com.greenfriends.zeroway.data.TipResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService {
    private var retrofit = RetrofitClient.getRetrofit()
    private lateinit var tipView: TipView
    private lateinit var termView: TermView

    fun setTipView(tipView: TipView) {
        this.tipView = tipView
    }

    fun setTermView(termView: TermView) {
        this.termView = termView
    }


    fun getTip() {
        val homeService = retrofit?.create(HomeRetrofitInterface::class.java)
        homeService!!.getTip().enqueue(object : Callback<List<TipResponse>> {
            override fun onResponse(
                call: Call<List<TipResponse>>,
                response: Response<List<TipResponse>>
            ) {
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

    fun getTerm(keyword:String?,page:Int?,size:Int?) {
        val homeService = retrofit?.create(HomeRetrofitInterface::class.java)
        homeService!!.getTerm(
            keyword, page, size
        ).enqueue(object : Callback<List<TermResponse>> {
            override fun onResponse(
                call: Call<List<TermResponse>>,
                response: Response<List<TermResponse>>
            ) {
                Log.d("TERM/SUCCESS", response.toString())
                if (response.isSuccessful) {
                    termView.onTermSuccess(response.body()!!)
                } else {
                    termView.onTermFailure()
                }
            }

            override fun onFailure(call: Call<List<TermResponse>>, t: Throwable) {
                Log.d("TERM/FAILURE", t.message.toString())
            }
        })
    }

}