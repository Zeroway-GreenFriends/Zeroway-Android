package com.greenfriends.zeroway.api

import com.greenfriends.zeroway.data.Result

interface LoginView {
    fun onLoginSuccess(result: Result)
    fun onLoginFailure()
}