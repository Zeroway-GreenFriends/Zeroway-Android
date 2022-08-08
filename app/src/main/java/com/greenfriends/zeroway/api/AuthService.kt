package com.greenfriends.zeroway.api

import android.util.Log
import com.greenfriends.zeroway.data.LoginResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthService {
    private var retrofit = RetrofitClient.getRetrofit()
    private lateinit var loginView: LoginView

    fun setLoginView(loginView: LoginView) {
        this.loginView = loginView
    }

    fun login(profileImg: MultipartBody.Part, user: RequestBody) {
        val authService = retrofit?.create(AuthRetrofitInterface::class.java)
        authService!!.login(profileImg, user).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("LOGIN/SUCCESS", response.body().toString())
                if (response.isSuccessful) {
                    loginView.onLoginSuccess()
                } else {
                    loginView.onLoginFailure()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("LOGIN/FAILURE", t.message.toString())
            }
        })
    }
}