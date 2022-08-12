package com.greenfriends.zeroway.repository.signup

import com.greenfriends.zeroway.model.Result

interface SignUpView {
    fun onSignUpSuccess(result: Result)
    fun onSignUpFailure()
}