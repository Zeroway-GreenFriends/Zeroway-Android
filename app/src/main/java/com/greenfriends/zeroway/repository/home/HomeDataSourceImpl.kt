package com.greenfriends.zeroway.data.source.remote.home

<<<<<<< HEAD:app/src/main/java/com/greenfriends/zeroway/data/source/remote/home/HomeDataSourceImpl.kt
import com.greenfriends.zeroway.data.model.ChallengeResponse
import com.greenfriends.zeroway.data.model.TermResponse
import com.greenfriends.zeroway.data.model.TipResponse
import com.greenfriends.zeroway.data.api.ChallengeRetrofitInterface
import com.greenfriends.zeroway.data.api.HomeRetrofitInterface
import com.greenfriends.zeroway.data.api.RetrofitClient
import com.greenfriends.zeroway.repository.home.HomeDataSource
=======
import com.greenfriends.zeroway.model.ChallengeResponse
import com.greenfriends.zeroway.model.TermResponse
import com.greenfriends.zeroway.model.TipResponse
import com.greenfriends.zeroway.network.ChallengeRetrofitInterface
import com.greenfriends.zeroway.network.HomeRetrofitInterface
import com.greenfriends.zeroway.network.RetrofitClient
>>>>>>> parent of 39dddb6 (fix: 패키지 구조 반영):app/src/main/java/com/greenfriends/zeroway/repository/home/HomeDataSourceImpl.kt
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