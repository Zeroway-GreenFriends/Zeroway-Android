package com.greenfriends.zeroway.network

import com.greenfriends.zeroway.model.ChallengeLevelResponse
import com.greenfriends.zeroway.model.ChallengeListResponse
import com.greenfriends.zeroway.model.ChallengeResponse
import com.greenfriends.zeroway.model.CommunityResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ChallengeRetrofitInterface {

    @GET("challenge")
    suspend fun getChallenge(
        @Header("Bearer") accessToken: String
    ): Response<ChallengeResponse>

    @GET("challenge/list")
    suspend fun getChallengeList(
        @Header("Bearer") accessToken: String
    ): Response<List<ChallengeListResponse>>

    @PATCH("challenge/{challenge_id}/complete")
    suspend fun updateChallenge(
        @Header("Bearer") accessToken: String,
        @Path("challenge_id") challengeId: Long
    ): Response<ChallengeLevelResponse>

}