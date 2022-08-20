package com.greenfriends.zeroway.network

import com.greenfriends.zeroway.model.ChallengeListResponse

interface ChallengeListView {
    fun onChallengeListSuccess(result: List<ChallengeListResponse>)
    fun onChallengeListFailure()
}