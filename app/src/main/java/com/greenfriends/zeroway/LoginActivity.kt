package com.greenfriends.zeroway

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.greenfriends.zeroway.databinding.ActivityLoginBinding
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        var keyHash = Utility.getKeyHash(this)
        Log.e(ContentValues.TAG, "해시 키 값 : ${keyHash}")

        binding.loginKakaoLoginIv.setOnClickListener {

            UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
                if (error != null) {
                    Log.e("로그인 실패", error.toString())
                } else if (token != null) {
                    Log.i("로그인 성공", "로그인 성공 ${token.accessToken}")
                    //닉네임, 이메일 받기
                    UserApiClient.instance.me { user, error ->
                        if (error != null) {
                            Log.e(ContentValues.TAG, "사용자 정보 요청 실패", error)
                        } else if (user != null) {
                            Log.i(
                                ContentValues.TAG, "사용자 정보 요청 성공" +
                                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                                        "\n이메일: ${user.kakaoAccount?.email}" +
                                        "\n사진: ${user.kakaoAccount?.profile?.profileImageUrl}"
                            )
                            //서버 연동
                        }
                    }
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        }

        binding.loginGoogleLoginIv.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.loginNaverLoginIv.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        setContentView(binding.root)
    }
}