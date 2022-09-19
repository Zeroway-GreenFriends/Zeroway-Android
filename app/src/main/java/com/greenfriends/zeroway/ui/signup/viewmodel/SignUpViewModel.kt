package com.greenfriends.zeroway.ui.signup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenfriends.zeroway.data.model.UserIdentification
import com.greenfriends.zeroway.data.repository.signup.SignUpRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class SignUpViewModel(private val signUpRepository: SignUpRepository) : ViewModel() {

    private val _idCheck = MutableLiveData<Boolean>(null)
    val idCheck: LiveData<Boolean> = _idCheck

    private val _idCheckEvent = MutableLiveData(false)
    val idCheckEvent: LiveData<Boolean> = _idCheckEvent

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _nickname = MutableLiveData<String>()
    val nickname: LiveData<String> = _nickname

    private val _provider = MutableLiveData<String>()
    val provider: LiveData<String> = _provider

    private val _file = MutableLiveData<File>()
    val file: LiveData<File> = _file

    private val _isSetImage = MutableLiveData(false)
    val isSetImage: LiveData<Boolean> = _isSetImage

    private val _userIdentification = MutableLiveData<UserIdentification>()
    val userIdentification: LiveData<UserIdentification> = _userIdentification

    fun setEmail(email: String) {
        _email.value = email
    }

    fun getEmail(): String? {
        return _email.value
    }

    fun setNickname(nickname: String) {
        _nickname.value = nickname
    }

    fun getNickname(): String? {
        return _nickname.value
    }

    fun setProvider(provider: String) {
        _provider.value = provider
    }

    fun getProvider(): String? {
        return _provider.value
    }

    fun setFile(file: File) {
        _file.value = file
    }

    fun getFile(): File? {
        return _file.value
    }

    fun setIsSetImage() {
        _isSetImage.value = true
    }

    fun getIsSetImage(): Boolean? {
        return _isSetImage.value
    }

    fun getIdCheckEvent(): Boolean? {
        return _idCheckEvent.value
    }

    fun signUp(profileImg: MultipartBody.Part?, user: RequestBody) {
        viewModelScope.launch {
            val response = signUpRepository.signUp(profileImg, user)
            if (response.isSuccessful) {
                val accessToken = response.body()!!.result.accessToken
                val refreshToken = response.body()!!.result.refreshToken
                val profileImgUrl = response.body()!!.result.profileImgUrl
                _userIdentification.value =
                    UserIdentification(accessToken, refreshToken, profileImgUrl)
                Log.d("AUTH/SIGNUP/T", response.body().toString())
            } else {
                Log.d("AUTH/SIGNUP/F", response.errorBody()?.string()!!)
            }
        }
    }

    fun idCheck(nickname: String) {
        viewModelScope.launch {
            val response = signUpRepository.idCheck(nickname)
            if (response.isSuccessful) {
                _idCheck.value = !response.body()!!.result
                _idCheckEvent.value = !response.body()!!.result
                Log.d("AUTH/IDCHECK/T", response.body().toString())
            } else {
                Log.d("AUTH/IDCHECK/F", response.errorBody()?.string()!!)
            }
        }
    }
}