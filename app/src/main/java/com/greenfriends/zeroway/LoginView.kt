package com.greenfriends.zeroway

import androidx.appcompat.app.AppCompatActivity

interface LoginView {
    fun onLoginSuccess(newUser: Boolean)
    fun onLoginFailure()
}