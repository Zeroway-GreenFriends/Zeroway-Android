package com.greenfriends.zeroway.api

import android.view.View
import com.greenfriends.zeroway.data.Result

interface LoginView {
    fun onLoginSuccess(result: Result)
    fun onLoginFailure()
    fun onClick(p0: View?)
}