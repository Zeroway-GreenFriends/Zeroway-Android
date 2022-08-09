package com.greenfriends.zeroway.api

import android.util.Log
import com.greenfriends.zeroway.data.AuthResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthService {
    private var retrofit = RetrofitClient.getRetrofit()
    private lateinit var loginView: LoginView
    private lateinit var signUpView: SignUpView

    fun setLoginView(loginView: LoginView) {
        this.loginView = loginView
    }

    fun setSignUpView(signUpView: SignUpView) {
        this.signUpView = signUpView
    }

    fun login(email: String) {
        val authService = retrofit?.create(AuthRetrofitInterface::class.java)
        authService!!.login(email).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("API/LOGIN/SUCCESS", response.body().toString())
                if (response.isSuccessful) {
                    loginView.onLoginSuccess(response.body()!!.result)
                } else {
                    loginView.onLoginFailure()
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("API/LOGIN/FAILURE", t.message.toString())
            }
        })
    }

    fun signUp(profileImg: MultipartBody.Part, user: RequestBody) {
        val authService = retrofit?.create(AuthRetrofitInterface::class.java)
        authService!!.signUp(profileImg, user).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("API/SIGNUP/SUCCESS", response.body().toString())
                if (response.isSuccessful) {
                    signUpView.onSignUpSuccess(response.body()!!.result)
                } else {
                    signUpView.onSignUpFailure()
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("API/SIGNUP/FAILURE", t.message.toString())
            }
        })
    }
}