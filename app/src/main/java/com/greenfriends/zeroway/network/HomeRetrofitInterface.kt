package com.greenfriends.zeroway.network

<<<<<<< HEAD:app/src/main/java/com/greenfriends/zeroway/data/api/HomeRetrofitInterface.kt
import com.greenfriends.zeroway.data.model.TermResponse
import com.greenfriends.zeroway.data.model.TipResponse
=======
import com.greenfriends.zeroway.model.ChallengeResponse
import com.greenfriends.zeroway.model.TermResponse
import com.greenfriends.zeroway.model.TipResponse
>>>>>>> parent of 39dddb6 (fix: 패키지 구조 반영):app/src/main/java/com/greenfriends/zeroway/network/HomeRetrofitInterface.kt
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface HomeRetrofitInterface {
    @GET("tip")
    fun getTip(): Response<List<TipResponse>>

    @GET("term")
    fun getTerm(
        @Query("keyword") keyword: String? = null,
        @Query("keyword") page: Int? = null,
        @Query("keyword") size: Int? = null,
    ): Response<List<TermResponse>>
}