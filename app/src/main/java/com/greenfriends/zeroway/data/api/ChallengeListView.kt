package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.ChallengeListResponse

interface ChallengeListView {
    fun onChallengeListSuccess(result: List<ChallengeListResponse>)
    fun onChallengeListFailure()
}