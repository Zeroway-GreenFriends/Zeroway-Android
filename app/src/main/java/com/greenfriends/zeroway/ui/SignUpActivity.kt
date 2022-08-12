package com.greenfriends.zeroway.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.greenfriends.zeroway.MainActivity
import com.greenfriends.zeroway.repository.api.AuthService
import com.greenfriends.zeroway.repository.signup.SignUpView
import com.greenfriends.zeroway.model.Result
import com.greenfriends.zeroway.model.User
import com.greenfriends.zeroway.databinding.ActivitySignUpBinding
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class SignUpActivity : AppCompatActivity(), SignUpView {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private var gson = Gson()
    private lateinit var file: File
    private var email: String = ""
    private var nickname: String = ""
    private var provider: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        email = intent.getStringExtra("email").toString()
        Log.d("EMAIL", email)
        provider = intent.getStringExtra("provider").toString()

        setPermission()
        setActivityResultLauncher()
        setProfileImage()

        signUp()
    }

    private fun setProfileImage() {
        binding.signupProfileIv.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.type = "image/*"
            launcher.launch(intent)
        }
    }

    private fun setNickName() {
        nickname = binding.signupNicknameEt.text.toString()
    }

    private fun setPermission() {
        if (Build.VERSION.SDK_INT >= 30) {
            if (!Environment.isExternalStorageManager()) {
                val getPermission = Intent()
                getPermission.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION;
                startActivity(getPermission);
            }
        }
    }

    private fun setActivityResultLauncher() {
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    val uri = data?.data as Uri
                    Glide.with(applicationContext).load(uri).into(binding.signupProfileIv)
                    // 이미지 절대 경로
                    Log.d("TEST", getAbsolutelyPath(uri, this))
                    file = File(getAbsolutelyPath(uri, this))
                }
            }
    }

    private fun getAbsolutelyPath(path: Uri?, context: Context): String {
        val proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        val index = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor?.moveToFirst()
        val result = cursor?.getString(index!!)

        return result!!
    }

    private fun signUp() {
        binding.signupStartBtn.setOnClickListener {
            setNickName()
            val user = User(email, nickname, provider)
            val userRequestBody =
                gson.toJson(user).toRequestBody("application/json; charset=utf-8".toMediaType())
            val fileRequestBody = file.asRequestBody("text/x-markdown; charset=utf-8".toMediaType())
            val multipartBodyPartFile =
                MultipartBody.Part.createFormData("profileImg", file.name, fileRequestBody)

            val authService = AuthService()
            authService.setSignUpView(this)
            authService.signUp(multipartBodyPartFile, userRequestBody)
        }
    }

    override fun onSignUpSuccess(result: Result) {
        Log.d("SIGNUP/SUCCESS", "신규 회원 가입 성공")
        // jwt 저장하는 코드를 추가해야 하는 곳입니다.
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onSignUpFailure() {
        Log.d("SIGNUP/FAILURE", "신규 회원 가입 실패")
    }
}