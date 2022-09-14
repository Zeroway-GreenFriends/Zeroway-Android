package com.greenfriends.zeroway.repository.challenge

import com.greenfriends.zeroway.data.model.*
import com.greenfriends.zeroway.data.api.ChallengeRetrofitInterface
import com.greenfriends.zeroway.data.api.HomeRetrofitInterface
import com.greenfriends.zeroway.data.api.RetrofitClient
import retrofit2.Response

class ChallengeDataSourceImpl: ChallengeDataSource {

    private val challengeService =
        RetrofitClient.getRetrofit()?.create(ChallengeRetrofitInterface::class.java)

    override suspend fun getUserChallenge(accessToken: String): Response<ChallengeResponse> {
        return challengeService!!.getChallenge(accessToken)
    }

    override suspend fun getChallengeList(accessToken: String): Response<List<ChallengeListResponse>> {
        return challengeService!!.getChallengeList(accessToken)
    }

    override suspend fun updateChallenge(accessToken: String, challengeId: Long): Response<ChallengeLevelResponse> {
        return challengeService!!.updateChallenge(accessToken,challengeId)
    }

}