package com.greenfriends.zeroway.repository.challenge

import com.greenfriends.zeroway.data.model.*
import retrofit2.Response

interface ChallengeDataSource {

    suspend fun getUserChallenge(accessToken: String): Response<ChallengeResponse>

    suspend fun getChallengeList(accessToken: String): Response<List<ChallengeListResponse>>

    suspend fun updateChallenge(accessToken: String, challengeId: Long): Response<ChallengeLevelResponse>

}