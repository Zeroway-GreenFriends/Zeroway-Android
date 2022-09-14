package com.greenfriends.zeroway.repository.home

import com.greenfriends.zeroway.data.model.ChallengeResponse
import com.greenfriends.zeroway.data.model.TermResponse
import com.greenfriends.zeroway.data.model.TipResponse
import retrofit2.Response

class HomeRepository(private val homeDataSourceImpl: HomeDataSourceImpl) {

    suspend fun getUserChallenge(accessToken: String): Response<ChallengeResponse> {
        return homeDataSourceImpl.getUserChallenge(accessToken)
    }

    suspend fun getTerm(keyword: String?, page: Int?, size: Int?): Response<List<TermResponse>> {
        return homeDataSourceImpl.getTerm(keyword,page,size)
    }

    suspend fun getTip(): Response<List<TipResponse>> {
        return homeDataSourceImpl.getTip()
    }

    suspend fun getTermSearch(keyword: String?, page: Int?, size: Int?): Response<List<TermResponse>> {
        return homeDataSourceImpl.getTermSearch(keyword,page,size)
    }

}