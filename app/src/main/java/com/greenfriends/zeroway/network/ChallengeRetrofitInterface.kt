package com.greenfriends.zeroway.network

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
    fun getChallenge(
        @Header("Bearer") accessToken: String
    ): Call<ChallengeResponse>

    @GET("challenge/list")
    fun getChallengeList(
        @Header("Bearer") accessToken: String
    ): Call<List<ChallengeListResponse>>


}