package com.greenfriends.zeroway.network

import com.greenfriends.zeroway.model.ChallengeResponse

interface ChallengeView {
    fun onChallengeSuccess(result: ChallengeResponse)
    fun onChallengeFailure()
}