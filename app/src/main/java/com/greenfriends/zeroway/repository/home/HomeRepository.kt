package com.greenfriends.zeroway.repository.home

<<<<<<< HEAD:app/src/main/java/com/greenfriends/zeroway/data/source/remote/home/HomeRepository.kt
import com.greenfriends.zeroway.data.model.ChallengeResponse
import com.greenfriends.zeroway.data.model.TermResponse
import com.greenfriends.zeroway.data.model.TipResponse
import com.greenfriends.zeroway.data.source.remote.home.HomeDataSourceImpl
=======
import com.greenfriends.zeroway.model.ChallengeResponse
import com.greenfriends.zeroway.model.TermResponse
import com.greenfriends.zeroway.model.TipResponse
>>>>>>> parent of 39dddb6 (fix: 패키지 구조 반영):app/src/main/java/com/greenfriends/zeroway/repository/home/HomeRepository.kt
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