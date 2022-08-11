package com.greenfriends.zeroway.repository.login

import com.greenfriends.zeroway.model.Result

interface LoginView {
    fun onLoginSuccess(result: Result)
    fun onLoginFailure()
}