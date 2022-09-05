package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.Result

interface LoginView {
    fun onLoginSuccess(result: Result)
    fun onLoginFailure()
}