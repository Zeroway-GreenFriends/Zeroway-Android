package com.greenfriends.zeroway.data.repository.home

import com.greenfriends.zeroway.data.model.ChallengeLevelResponse
import com.greenfriends.zeroway.data.model.ChallengeListResponse
import com.greenfriends.zeroway.data.model.ChallengeResponse
import com.greenfriends.zeroway.data.source.remote.home.ChallengeDataSourceImpl
import retrofit2.Response

class ChallengeRepository(private val homeDataSourceImpl: ChallengeDataSourceImpl) {

    suspend fun getUserChallenge(accessToken: String): Response<ChallengeResponse> {
        return homeDataSourceImpl.getUserChallenge(accessToken)
    }

    suspend fun getChallengeList(accessToken: String): Response<List<ChallengeListResponse>> {
        return homeDataSourceImpl.getChallengeList(accessToken)
    }

    suspend fun updateChallenge(accessToken: String, challengeId: Long): Response<ChallengeLevelResponse> {
        return homeDataSourceImpl.updateChallenge(accessToken,challengeId)
    }
}