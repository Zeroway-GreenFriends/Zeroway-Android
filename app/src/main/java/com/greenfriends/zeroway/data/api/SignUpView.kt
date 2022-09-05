package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.Result

interface SignUpView {
    fun onSignUpSuccess(result: Result)
    fun onSignUpFailure()
}