package com.greenfriends.zeroway.data.source.remote.home

import com.greenfriends.zeroway.data.model.ChallengeResponse
import com.greenfriends.zeroway.data.model.TermResponse
import com.greenfriends.zeroway.data.model.TipResponse
import com.greenfriends.zeroway.data.api.ChallengeRetrofitInterface
import com.greenfriends.zeroway.data.api.HomeRetrofitInterface
import com.greenfriends.zeroway.data.api.RetrofitClient
import com.greenfriends.zeroway.repository.home.HomeDataSource
import retrofit2.Response

class HomeDataSourceImpl: HomeDataSource {

    private val challengeService =
        RetrofitClient.getRetrofit()?.create(ChallengeRetrofitInterface::class.java)

    private val homeService =
        RetrofitClient.getRetrofit()?.create(HomeRetrofitInterface::class.java)

    override suspend fun getUserChallenge(accessToken: String): Response<ChallengeResponse> {
        return challengeService!!.getChallenge(accessToken)
    }

    override suspend fun getTerm(keyword: String?, page: Int?, size: Int?): Response<List<TermResponse>> {
        return homeService!!.getTerm(keyword, page, size)
    }

    override suspend fun getTip(): Response<List<TipResponse>> {
        return homeService!!.getTip()
    }

    override suspend fun getTermSearch(
        keyword: String?,
        page: Int?,
        size: Int?
    ): Response<List<TermResponse>> {
        TODO("Not yet implemented")
    }

//    override suspend fun getTermSearch(keyword: String?, page: Int?, size: Int?): Response<List<TermResponse>> {
//        //return homeService!!.getTermSearch(keyword, page, size)
//    }

}