package com.greenfriends.zeroway.network

import android.util.Log
import com.greenfriends.zeroway.api.StoreListView
import com.greenfriends.zeroway.api.TermSearchView
import com.greenfriends.zeroway.api.TermView
import com.greenfriends.zeroway.api.TipView
import com.greenfriends.zeroway.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChallengeService {
    private var retrofit = RetrofitClient.getRetrofit()
    private lateinit var challengeView: ChallengeView
    private lateinit var challengeListView: ChallengeListView

    fun setChallengeView(challengeView: ChallengeView) {
        this.challengeView = challengeView
    }

    fun setChallengeListView(challengeListView: ChallengeListView) {
        this.challengeListView = challengeListView
    }

    fun getChallenge(token: String) {
        val challengeService = retrofit?.create(ChallengeRetrofitInterface::class.java)
        challengeService!!.getChallenge(token).enqueue(object : Callback<ChallengeResponse> {

            override fun onResponse(
                call: Call<ChallengeResponse>,
                response: Response<ChallengeResponse>
            ) {
                Log.d("CHALLENGE/SUCCESS", response.toString())
                if (response.isSuccessful) {
                    challengeView.onChallengeSuccess(response.body()!!)
                } else {
                    challengeView.onChallengeFailure()
                }
            }

            override fun onFailure(call: Call<ChallengeResponse>, t: Throwable) {
                Log.d("CHALLENGE/FAILURE", t.message.toString())
            }

        })
    }

    fun getChallengeList(token: String) {
        val challengeService = retrofit?.create(ChallengeRetrofitInterface::class.java)
        challengeService!!.getChallengeList(token).enqueue(object : Callback<List<ChallengeListResponse>> {

            override fun onResponse(
                call: Call<List<ChallengeListResponse>>,
                response: Response<List<ChallengeListResponse>>
            ) {
                Log.d("CHALLENGE LIST/SUCCESS", response.toString())
                if (response.isSuccessful) {
                    challengeListView.onChallengeListSuccess(response.body()!!)
                } else {
                    challengeListView.onChallengeListFailure()
                }
            }

            override fun onFailure(call: Call<List<ChallengeListResponse>>, t: Throwable) {
                Log.d("CHALLENGE LIST/FAILURE", t.message.toString())
            }

        })
    }
}