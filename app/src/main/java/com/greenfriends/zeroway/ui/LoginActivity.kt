package com.greenfriends.zeroway.ui

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.ActivityLoginBinding
import com.greenfriends.zeroway.model.LoginRequest
import com.greenfriends.zeroway.model.Result
import com.greenfriends.zeroway.network.AuthService
import com.greenfriends.zeroway.network.LoginView
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse

import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Constraints
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.greenfriends.zeroway.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity(), LoginView {
    private lateinit var binding: ActivityLoginBinding
    private var email: String = ""
    private var provider: String = ""

    //firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth
    //google client
    private lateinit var googleSignInClient: GoogleSignInClient
    //private const val TAG = "GoogleActivity"
    private val RC_SIGN_IN = 99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClickKakaoLogin()
        onClickNaverLogin()
        onClickGoogleLogin()
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

    private fun login() {
        val authService = AuthService()
        authService.setLoginView(this)
        authService.login(LoginRequest(email))
    }

    private fun onClickKakaoLogin() {
        binding.loginKakaoLoginIv.setOnClickListener {
            kakaoLogin()
        }
    }

    private fun onClickNaverLogin() {
        binding.loginNaverLoginIv.setOnClickListener {
            naverLogin()
        }
    }

    private fun onClickGoogleLogin() {
        binding.loginGoogleLoginIv.setOnClickListener {
            googleLogin()
        }
    }

    private fun initializeNaverClient() {
        val naverClientId = getString(R.string.naver_client_id)
        val naverClientSecret = getString(R.string.naver_client_secret)
        val naverClientName = getString(R.string.app_name)
        NaverIdLoginSDK.initialize(this, naverClientId, naverClientSecret, naverClientName)
    }


    private fun kakaoLogin() {
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
                        login()
                        startMainActivity()
                    }
                }
            }
        }
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

    private fun googleLogin() {
        //Google 로그인 옵션 구성. requestIdToken 및 Email 요청
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("IHNxsiEIEVYm7WjtNrI2dUuVp972")
            //'R.string.default_web_client_id' 에는 본인의 클라이언트 아이디를 넣어주시면 됩니다.
            //저는 스트링을 따로 빼서 저렇게 사용했지만 스트링을 통째로 넣으셔도 됩니다.
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        //firebase auth 객체
        firebaseAuth = FirebaseAuth.getInstance()


    }

    // onStart. 유저가 앱에 이미 구글 로그인을 했는지 확인
    public override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if(account!==null){ // 이미 로그인 되어있을시 바로 메인 액티비티로 이동
            startMainActivity()
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)

            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("LoginActivity", "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d("LoginActivity", "firebaseAuthWithGoogle:" + acct.id!!)

        //Google SignInAccount 객체에서 ID 토큰을 가져와서 Firebase Auth로 교환하고 Firebase에 인증
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.w("LoginActivity", "firebaseAuthWithGoogle 성공", task.exception)
                    startMainActivity()
                } else {
                    Log.w("LoginActivity", "firebaseAuthWithGoogle 실패", task.exception)
                    Snackbar.make(findViewById<ConstraintLayout>(R.id.login_layout), "로그인에 실패하였습니다.", Snackbar.LENGTH_SHORT).show()
                }
            }
    }

    private fun saveJwt(jwt: String?) {
        val sharedPreferences = getSharedPreferences("auth", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("jwt", jwt)
        editor.apply()
    }

    override fun onLoginSuccess(result: Result) {
        Log.d("LOGIN/SUCCESS", "기존 회원 로그인 성공")
        saveJwt(result.accessToken)
        startMainActivity()
        finish()
    }

    override fun onLoginFailure() {
        Log.d("LOGIN/FAILURE", "기존 회원 로그인 실패 -> 미가입 회원으로 신규 가입이 필요합니다.")
        startSignUpActivity()
        finish()
    }
}