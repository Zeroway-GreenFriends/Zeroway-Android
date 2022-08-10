package com.greenfriends.zeroway

import android.util.Log
import android.view.View
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.net.HttpCookie.parse
import java.text.Normalizer

class AuthService {
    private lateinit var loginView: LoginView

    fun setLoginView(loginView: LoginView){
        this.loginView = loginView
    }

    fun login(profileImg: File,email:String,nickname:String,provider:String){
        //retrofit 호출
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        val jsonArray = arrayListOf<String>()
        val jsonObject = JSONObject("{\"email\":\"${email}\",\"nickname\":\"${nickname}\",\"provider\":\"${provider}\"}").toString()
//        val jsonBody = RequestBody.cre(parse("application/json"),jsonObject)

        //enqueue라는 메소드를 통해 응답 처리
        authService.login(
            FormDataUtil.getImageBody("profileImg",profileImg),
            jsonObject.toRequestBody("application/json".toMediaTypeOrNull())
        ).enqueue(object : Callback<LoginResponse> {
            //응답이 왔을 때 처리
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("LOGIN/SUCCESS",response.toString())
                val resp: Result? = response.body()!!.result
                val newUser: Boolean? = response.body()!!.result!!.newUser
                loginView.onLoginSuccess(newUser!!)
//                when(val code = resp.code){
//                    200-> Log.d("Login",code.toString())
//                    else -> loginView.onLoginFailure()
//                }
                Log.d("LOGIN/SUCCESS",resp.toString())
            }
            //연결 실패 시 실행
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("LOGIN/FAIL",t.message.toString())
            }

        })
        Log.d("LOGIN","HELLO")
    }

}