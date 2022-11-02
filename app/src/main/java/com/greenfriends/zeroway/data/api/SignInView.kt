package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.UserIdentification

interface SignInView {
    fun onSignInSuccess(result: UserIdentification)
    fun onSignInFailure()
}