package com.greenfriends.zeroway.network

import com.greenfriends.zeroway.model.Result

interface SignUpView {
    fun onSignUpSuccess(result: Result)
    fun onSignUpFailure()
}