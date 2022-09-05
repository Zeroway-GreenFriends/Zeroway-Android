package com.greenfriends.zeroway.data.api

import android.util.Log
import com.greenfriends.zeroway.data.model.StoreResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreService {
    private var retrofit = RetrofitClient.getRetrofit()
    private lateinit var storeListView: StoreListView

    fun setStoreListView(storeListView: StoreListView) {
        this.storeListView = storeListView
    }

    fun getStoreList(keyword: String?, page: Int?, size: Int?) {
        val storeService = retrofit?.create(StoreRetrofitInterface::class.java)
        storeService!!.getStoreList(
            keyword, page, size
        ).enqueue(object : Callback<List<StoreResponse>> {
            override fun onResponse(
                call: Call<List<StoreResponse>>,
                response: Response<List<StoreResponse>>
            ) {
                Log.d("STORE/SUCCESS", response.toString())
                if (response.isSuccessful) {
                    storeListView.onStoreListSuccess(response.body()!!)
                } else {
                    storeListView.onStoreListFailure()
                }
            }

            override fun onFailure(call: Call<List<StoreResponse>>, t: Throwable) {
                Log.d("STORE/FAILURE", t.message.toString())
            }

        })
    }

}