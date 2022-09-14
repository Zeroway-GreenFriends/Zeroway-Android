package com.greenfriends.zeroway.ui.signup.view

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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.greenfriends.zeroway.data.model.User
import com.greenfriends.zeroway.databinding.ActivitySignUpBinding
import com.greenfriends.zeroway.ui.MainActivity
import com.greenfriends.zeroway.ui.common.ViewModelFactory
import com.greenfriends.zeroway.ui.signup.viewmodel.SignUpViewModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class SignUpActivity : AppCompatActivity() {

    private val viewModel: SignUpViewModel by viewModels { ViewModelFactory() }
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private var gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this

        setObserve()
        setEmailAndProvider()
        setPermission()
        setActivityResultLauncher()
        setProfileImage()

        authentication()
    }

    private fun setObserve() {
        viewModel.idCheck.observe(
            this
        ) { idCheck ->
            binding.idCheck = idCheck
        }
        viewModel.idCheckEvent.observe(
            this
        ) { idCheckEvent ->
            binding.idCheckEvent = idCheckEvent

        }
        viewModel.userIdentification.observe(
            this
        ) { userIdentification ->
            saveJwt(userIdentification.accessToken)
            saveProfileImgUrl(userIdentification.profileImgUrl)
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun setEmailAndProvider() {
        viewModel.setEmail(intent.getStringExtra("email").toString())
        viewModel.setProvider(intent.getStringExtra("provider").toString())
    }

    private fun setProfileImage() {
        binding.signupProfileIv.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.type = "image/*"
            launcher.launch(intent)
        }
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
                    viewModel.setFile(File(getAbsolutelyPath(uri, this)))
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

    private fun authentication() {
        binding.signupAuthBtn.setOnClickListener {
            viewModel.setNickname(binding.signupNicknameEt.text.toString())
            when (viewModel.getIdCheckEvent()) {
                false -> viewModel.idCheck(viewModel.getNickname()!!)
                else -> {
                    val user = User(
                        viewModel.getEmail()!!,
                        viewModel.getNickname()!!,
                        viewModel.getProvider()!!
                    )
                    val userRequestBody =
                        gson.toJson(user)
                            .toRequestBody("application/json; charset=utf-8".toMediaType())
                    val fileRequestBody = viewModel.getFile()!!
                        .asRequestBody("image/jpeg; charset=utf-8".toMediaType())
                    val multipartBodyPartFile =
                        MultipartBody.Part.createFormData(
                            "profileImg",
                            viewModel.getFile()!!.name,
                            fileRequestBody
                        )
                    viewModel.signUp(multipartBodyPartFile, userRequestBody)
                }
            }
        }
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
}