package com.greenfriends.zeroway.ui.community.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenfriends.zeroway.data.repository.community.CommunityPostRegisterRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class CommunityPostRegisterViewModel(private val communityPostRepository: CommunityPostRegisterRepository) :
    ViewModel() {

    private val _isChallenge = MutableLiveData<Boolean>(false)
    val isChallenge: LiveData<Boolean> = _isChallenge

    private val _imageUrl = MutableLiveData<List<String>>()
    val imageUrl: LiveData<List<String>> = _imageUrl

    private val _setPostIsSuccess = MutableLiveData<Boolean?>(null)
    val setPostIsSuccess: LiveData<Boolean?> = _setPostIsSuccess

    fun setIsChallenge() {
        _isChallenge.value = !_isChallenge.value!!
    }

    fun setImageUrl(imageUrl: String) {
        _imageUrl.value = _imageUrl.value?.plus(imageUrl) ?: listOf(imageUrl)
    }

    fun setPost(accessToken: String, images: List<MultipartBody.Part>, post: RequestBody) {
        viewModelScope.launch {
            val response = communityPostRepository.setPost(accessToken, images, post)
            if (response.isSuccessful) {
                _setPostIsSuccess.value = response.isSuccessful
                Log.d("COMMUNITY/REGISTER/T", response.body().toString())
            } else {
                Log.d("COMMUNITY/REGISTER/F", response.toString())
            }
        }
    }

    fun getIsChallenge(): Boolean? {
        return _isChallenge.value
    }

    fun getImageUrls(): List<String>? {
        return _imageUrl.value
    }
}