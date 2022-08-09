package com.greenfriends.zeroway.api

import com.greenfriends.zeroway.data.Result

interface SignUpView {
    fun onSignUpSuccess(result: Result)
    fun onSignUpFailure()
}