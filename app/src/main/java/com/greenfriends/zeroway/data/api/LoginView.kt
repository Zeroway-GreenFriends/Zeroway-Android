package com.greenfriends.zeroway.data.api

import com.greenfriends.zeroway.data.model.UserIdentification

interface LoginView {
    fun onLoginSuccess(result: UserIdentification)
    fun onLoginFailure()
}