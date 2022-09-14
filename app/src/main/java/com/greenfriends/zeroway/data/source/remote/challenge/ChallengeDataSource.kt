package com.greenfriends.zeroway.data.source.remote.challenge

import com.greenfriends.zeroway.data.model.ChallengeLevelResponse
import com.greenfriends.zeroway.data.model.ChallengeListResponse
import com.greenfriends.zeroway.data.model.ChallengeResponse
import retrofit2.Response

interface ChallengeDataSource {

    suspend fun getUserChallenge(accessToken: String): Response<ChallengeResponse>

    suspend fun getChallengeList(accessToken: String): Response<List<ChallengeListResponse>>

    suspend fun updateChallenge(accessToken: String, challengeId: Long): Response<ChallengeLevelResponse>

}