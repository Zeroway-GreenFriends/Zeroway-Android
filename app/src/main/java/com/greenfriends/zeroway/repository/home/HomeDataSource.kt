package com.greenfriends.zeroway.repository.home

import com.greenfriends.zeroway.model.ChallengeResponse
import com.greenfriends.zeroway.model.TermResponse
import com.greenfriends.zeroway.model.TipResponse
import retrofit2.Response

interface HomeDataSource {

    suspend fun getUserChallenge(accessToken: String): Response<ChallengeResponse>

    suspend fun getTerm(keyword: String?, page: Int?, size: Int?): Response<List<TermResponse>>

    suspend fun getTip(): Response<List<TipResponse>>

    suspend fun getTermSearch(keyword: String?, page: Int?, size: Int?): Response<List<TermResponse>>

}