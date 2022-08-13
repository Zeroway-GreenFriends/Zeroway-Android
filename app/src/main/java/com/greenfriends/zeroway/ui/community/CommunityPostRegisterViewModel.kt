package com.greenfriends.zeroway.ui.community

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenfriends.zeroway.repository.community.CommunityPostRegisterRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class CommunityPostRegisterViewModel(private val communityPostRepository: CommunityPostRegisterRepository) :
    ViewModel() {

    private val _isChallenge = MutableLiveData<Boolean>(false)
    val isChallenge: LiveData<Boolean> = _isChallenge

    private val _imageUrl = MutableLiveData<List<String>>()
    val imageUrl: LiveData<List<String>> = _imageUrl

    fun setIsChallenge() {
        _isChallenge.value = !_isChallenge.value!!
    }

    fun setImageUrl(imageUrl: String) {
        _imageUrl.value = _imageUrl.value?.plus(imageUrl) ?: listOf(imageUrl)
    }

    fun setPost(accessToken: String, images: List<MultipartBody.Part>, post: RequestBody) {
        viewModelScope.launch {
            communityPostRepository.setPost(accessToken, images, post)
        }
    }
}