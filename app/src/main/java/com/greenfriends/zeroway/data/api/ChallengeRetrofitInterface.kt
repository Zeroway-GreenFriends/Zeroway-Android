package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.ChallengeLevelResponse
import com.greenfriends.zeroway.data.model.ChallengeListResponse
import com.greenfriends.zeroway.data.model.ChallengeResponse
import retrofit2.Call
import retrofit2.http.*

interface ChallengeRetrofitInterface {

    @GET("challenge")
    fun getChallenge(
        @Header("Bearer") accessToken: String
    ): Call<ChallengeResponse>

    @GET("challenge/list")
    fun getChallengeList(
        @Header("Bearer") accessToken: String
    ): Call<List<ChallengeListResponse>>

    @PATCH("challenge/{challenge_id}/complete")
    fun updateChallenge(
        @Header("Bearer") accessToken: String,
        @Path("challenge_id") challengeId: Long
    ): Call<ChallengeLevelResponse>

}