package com.greenfriends.zeroway.network

import com.greenfriends.zeroway.model.Result

interface LoginView {
    fun onLoginSuccess(result: Result)
    fun onLoginFailure()
}