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

    private val _isChallenge = MutableLiveData(false)
    val isChallenge: LiveData<Boolean> = _isChallenge

    private val _isReview = MutableLiveData(false)
    val isReview: LiveData<Boolean> = _isReview

    private val _imageUrls = MutableLiveData<List<String>>()
    val imageUrls: LiveData<List<String>> = _imageUrls

    private val _setPostIsSuccess = MutableLiveData<Boolean?>(null)
    val setPostIsSuccess: LiveData<Boolean?> = _setPostIsSuccess

    fun setIsChallenge() {
        _isChallenge.value = !_isChallenge.value!!
    }

    fun getIsChallenge(): Boolean? {
        return _isChallenge.value
    }

    fun setIsReview() {
        _isReview.value = !_isReview.value!!
    }

    fun getIsReview(): Boolean? {
        return _isReview.value
    }

    fun setImageUrl(imageUrl: String) {
        _imageUrls.value = _imageUrls.value?.plus(imageUrl) ?: listOf(imageUrl)
    }

    fun getImageUrls(): List<String>? {
        return _imageUrls.value
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
}