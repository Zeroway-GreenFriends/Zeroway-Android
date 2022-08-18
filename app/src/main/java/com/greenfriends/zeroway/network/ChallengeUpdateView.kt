package com.greenfriends.zeroway.network

import com.greenfriends.zeroway.model.ChallengeLevelResponse

interface ChallengeUpdateView {
    fun onChallengeUpdateSuccess(result: ChallengeLevelResponse)
    fun onChallengeUpdateFailure()
}