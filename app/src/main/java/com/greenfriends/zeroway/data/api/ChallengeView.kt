package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.ChallengeResponse

interface ChallengeView {
    fun onChallengeSuccess(result: ChallengeResponse)
    fun onChallengeFailure()
}