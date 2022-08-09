package com.greenfriends.zeroway

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.greenfriends.zeroway.api.AuthService
import com.greenfriends.zeroway.api.LoginView
import com.greenfriends.zeroway.data.Result
import com.greenfriends.zeroway.databinding.ActivityLoginBinding
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse

class LoginActivity : AppCompatActivity(), LoginView {
    private lateinit var binding: ActivityLoginBinding
    private var email: String = ""
    private var provider: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClickNaverLogin()
    }

    private fun setEmail(email: String) {
        this.email = email
    }

    private fun setProvider(provider: String) {
        this.provider = provider
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun startSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        intent.putExtra("email", email)
        intent.putExtra("provider", "NAVER")
        startActivity(intent)
        finish()
    }

    private fun login() {
        val authService = AuthService()
        authService.setLoginView(this)
        authService.login(email)
    }

    private fun onClickNaverLogin() {
        binding.loginNaverLoginIv.setOnClickListener {
            naverLogin()
        }
    }

    private fun initializeNaverClient() {
        val naverClientId = getString(R.string.naver_client_id)
        val naverClientSecret = getString(R.string.naver_client_secret)
        val naverClientName = getString(R.string.app_name)
        NaverIdLoginSDK.initialize(this, naverClientId, naverClientSecret, naverClientName)
    }

    private fun naverLogin() {
        initializeNaverClient()
        lateinit var naverToken: String

        val profileCallback = object : NidProfileCallback<NidProfileResponse> {
            override fun onSuccess(result: NidProfileResponse) {
                val userId = result.profile?.id
                setEmail(result.profile?.email.toString())
                setProvider("NAVER")
                login()
                Log.d("NAVER/LOGIN/SUCCESS", "id:${userId}, token: $naverToken")
            }

            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                Log.d(
                    "NAVER/LOGIN/FAILURE",
                    "errorCode: ${errorCode}, errorDescription: $errorDescription"
                )
            }

            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }
        }

        val oAuthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {
                naverToken = NaverIdLoginSDK.getAccessToken().toString()
                NidOAuthLogin().callProfileApi(profileCallback)
            }

            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                Log.d(
                    "NAVER/LOGIN/FAILURE",
                    "errorCode: ${errorCode}, errorDescription: $errorDescription"
                )
            }

            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }
        }
        NaverIdLoginSDK.authenticate(this, oAuthLoginCallback)
    }

    override fun onLoginSuccess(result: Result) {
        Log.d("LOGIN/SUCCESS", "기존 회원 로그인 성공")
        // jwt 저장하는 코드를 추가해야 하는 곳입니다.
        startMainActivity()
        finish()
    }

    override fun onLoginFailure() {
        Log.d("LOGIN/FAILURE", "기존 회원 로그인 실패 -> 미가입 회원으로 신규 가입이 필요합니다.")
        startSignUpActivity()
        finish()
    }
}