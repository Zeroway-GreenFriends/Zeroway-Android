package com.greenfriends.zeroway.data.api

import android.util.Log
import com.greenfriends.zeroway.data.model.*
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
                    Log.d("CHALLENGE", response.errorBody()?.string()!!)
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