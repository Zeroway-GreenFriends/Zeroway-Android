package com.greenfriends.zeroway.ui.communitypostdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenfriends.zeroway.model.CommunityPostDetailResponse
import com.greenfriends.zeroway.repository.communitypostdetail.CommunityPostDetailRepository
import kotlinx.coroutines.launch

class CommunityPostDetailViewModel(private val communityPostDetailRepository: CommunityPostDetailRepository) :
    ViewModel() {

    private val _communityPostDetailResponse = MutableLiveData<CommunityPostDetailResponse>()
    val communityPostDetailResponse: LiveData<CommunityPostDetailResponse> =
        _communityPostDetailResponse

    fun getPostDetail(accessToken: String, postId: String) {
        viewModelScope.launch {
            val response = communityPostDetailRepository.getPostDetail(accessToken, postId)
            Log.d("COMMUNITY/DETAIL", response.body().toString())
            if (response.isSuccessful) {
                _communityPostDetailResponse.value = response.body()!!
            } else {
                Log.d("COMMUNITY/DETAIL/F", response.errorBody()?.string()!!)
            }
        }
    }
}