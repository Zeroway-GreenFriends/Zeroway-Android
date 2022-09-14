package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.ChallengeLevelResponse
import com.greenfriends.zeroway.data.model.ChallengeListResponse
import com.greenfriends.zeroway.data.model.ChallengeResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ChallengeRetrofitInterface {

    @GET("challenge")
    suspend fun getChallenge(
        @Header("Bearer") accessToken: String
    ): Response<ChallengeResponse>

    @GET("challenge/list/today")
    suspend fun getChallengeList(
        @Header("Bearer") accessToken: String
    ): Response<List<ChallengeListResponse>>

    @PATCH("challenge/{challenge_id}/complete")
    suspend fun updateChallenge(
        @Header("Bearer") accessToken: String,
        @Path("challenge_id") challengeId: Long
    ): Response<ChallengeLevelResponse>

}