package com.greenfriends.zeroway.presentation.signin

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.data.api.AuthService
import com.greenfriends.zeroway.data.api.SignInView
import com.greenfriends.zeroway.data.model.SignInRequest
import com.greenfriends.zeroway.data.model.UserIdentification
import com.greenfriends.zeroway.databinding.ActivitySignInBinding
import com.greenfriends.zeroway.presentation.MainActivity
import com.greenfriends.zeroway.presentation.signup.view.SignUpActivity
import com.greenfriends.zeroway.util.binding.BindingActivity
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse

class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in),
    SignInView {

    private var email: String = ""
    private var provider: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onClickKakaoSignIn()
        onClickNaverSignIn()
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
        intent.putExtra("provider", provider)
        startActivity(intent)
        finish()
    }

    private fun signIn() {
        val authService = AuthService()
        authService.setSignInView(this)
        authService.signIn(SignInRequest(email))
    }

    private fun onClickKakaoSignIn() {
        binding.signInKakaoLoginIv.setOnClickListener {
            kakaoSignIn()
        }
    }

    private fun onClickNaverSignIn() {
        binding.signInNaverLoginIv.setOnClickListener {
            naverSignIn()
        }
    }

    private fun initializeNaverClient() {
        val naverClientId = getString(R.string.naver_client_id)
        val naverClientSecret = getString(R.string.naver_client_secret)
        val naverClientName = getString(R.string.app_name)
        NaverIdLoginSDK.initialize(this, naverClientId, naverClientSecret, naverClientName)
    }


    private fun kakaoSignIn() {
        UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
            if (error != null) {
                Log.e("카카오/로그인 실패", error.toString())
            } else if (token != null) {
                Log.i("카카오/로그인 성공", "카카오/로그인 성공 ${token.accessToken}")
                //닉네임, 이메일 받기
                UserApiClient.instance.me { user, error ->
                    if (error != null) {
                        Log.e(ContentValues.TAG, "카카오/사용자 정보 요청 실패", error)
                    } else if (user != null) {
                        Log.i(
                            ContentValues.TAG, "카카오/사용자 정보 요청 성공" +
                                    "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                                    "\n이메일: ${user.kakaoAccount?.email}" +
                                    "\n사진: ${user.kakaoAccount?.profile?.profileImageUrl}"
                        )
                        //서버 연동
                        setEmail(user.kakaoAccount?.email!!)
                        setProvider("KAKAO")
                        signIn()
                        startMainActivity()
                    }
                }
            }
        }
    }

    private fun naverSignIn() {
        initializeNaverClient()
        lateinit var naverToken: String

        val profileCallback = object : NidProfileCallback<NidProfileResponse> {
            override fun onSuccess(result: NidProfileResponse) {
                val userId = result.profile?.id
                setEmail(result.profile?.email.toString())
                setProvider("NAVER")
                signIn()
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

    private fun saveJwt(jwt: String?) {
        val sharedPreferences = getSharedPreferences("auth", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("jwt", jwt)
        editor.apply()
    }

    private fun saveProfileImgUrl(profileImgUrl: String?) {
        val sharedPreferences = getSharedPreferences("profile", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("profileImgUrl", profileImgUrl)
        editor.apply()
    }

    override fun onSignInSuccess(result: UserIdentification) {
        Log.d("LOGIN/SUCCESS", "기존 회원 로그인 성공")
        saveJwt(result.accessToken)
        saveProfileImgUrl(result.profileImgUrl)
        startMainActivity()
        finish()
    }

    override fun onSignInFailure() {
        Log.d("LOGIN/FAILURE", "기존 회원 로그인 실패 -> 미가입 회원으로 신규 가입이 필요합니다.")
        startSignUpActivity()
        finish()
    }
}