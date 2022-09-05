package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.ChallengeLevelResponse

interface ChallengeUpdateView {
    fun onChallengeUpdateSuccess(result: ChallengeLevelResponse)
    fun onChallengeUpdateFailure()
}