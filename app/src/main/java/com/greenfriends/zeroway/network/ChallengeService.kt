package com.greenfriends.zeroway.network

import android.util.Log
import com.greenfriends.zeroway.network.StoreListView
import com.greenfriends.zeroway.network.TermSearchView
import com.greenfriends.zeroway.network.TermView
import com.greenfriends.zeroway.network.TipView
import com.greenfriends.zeroway.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChallengeService {
    private var retrofit = RetrofitClient.getRetrofit()
    private lateinit var challengeView: ChallengeView
    private lateinit var challengeListView: ChallengeListView
    private lateinit var challengeUpdateView: ChallengeUpdateView

    fun setChallengeView(challengeView: ChallengeView) {
        this.challengeView = challengeView
    }

    fun setChallengeListView(challengeListView: ChallengeListView) {
        this.challengeListView = challengeListView
    }

    fun setChallengeUpdateView(challengeUpdateView: ChallengeUpdateView) {
        this.challengeUpdateView = challengeUpdateView
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

    fun updateChallenge(token: String, challengeId: Long) {
        val challengeService = retrofit?.create(ChallengeRetrofitInterface::class.java)
        challengeService!!.updateChallenge(token,challengeId).enqueue(object : Callback<ChallengeLevelResponse> {
            override fun onResponse(
                call: Call<ChallengeLevelResponse>,
                response: Response<ChallengeLevelResponse>
            ) {
                Log.d("UPDATE/SUCCESS", response.toString())
                if (response.isSuccessful) {
                    challengeUpdateView.onChallengeUpdateSuccess(response.body()!!)
                } else {
                    challengeUpdateView.onChallengeUpdateFailure()
                }
            }

            override fun onFailure(call: Call<ChallengeLevelResponse>, t: Throwable) {
                Log.d("UPDATE/FAILURE", t.message.toString())
            }


        })
    }
}