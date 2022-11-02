package com.greenfriends.zeroway.data.api

import android.util.Log
import com.greenfriends.zeroway.data.model.AuthResponse
import com.greenfriends.zeroway.data.model.SignInRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthService {

    private var retrofit = RetrofitClient.getRetrofit()
    private lateinit var signInView: SignInView

    fun setSignInView(signInView: SignInView) {
        this.signInView = signInView
    }

    fun signIn(loginRequest: SignInRequest) {
        val authService = retrofit?.create(AuthRetrofitInterface::class.java)
        authService!!.login(loginRequest).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("API/LOGIN/SUCCESS", response.body().toString())
                if (response.isSuccessful) {
                    signInView.onSignInSuccess(response.body()!!.result)
                } else {
                    signInView.onSignInFailure()
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("API/LOGIN/FAILURE", t.message.toString())
            }
        })
    }
}