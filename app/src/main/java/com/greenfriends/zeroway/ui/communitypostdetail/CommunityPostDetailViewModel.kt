package com.greenfriends.zeroway.ui.communitypostdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenfriends.zeroway.model.CommunityPostCommentRequest
import com.greenfriends.zeroway.model.CommunityPostDetailResponse
import com.greenfriends.zeroway.model.CommunityPostLikeRequest
import com.greenfriends.zeroway.repository.communitypostdetail.CommunityPostDetailRepository
import com.greenfriends.zeroway.ui.common.Event
import kotlinx.coroutines.launch

class CommunityPostDetailViewModel(private val communityPostDetailRepository: CommunityPostDetailRepository) :
    ViewModel() {

    private val _postId = MutableLiveData<String>()
    val postId: LiveData<String> = _postId

    private val _communityPostDetailResponse = MutableLiveData<CommunityPostDetailResponse>()
    val communityPostDetailResponse: LiveData<CommunityPostDetailResponse> =
        _communityPostDetailResponse

    private val _commentRegisterEvent = MutableLiveData<Event<CommunityPostCommentRequest>>()
    val commentRegisterEvent: LiveData<Event<CommunityPostCommentRequest>> = _commentRegisterEvent

    fun setPostId(postId: String) {
        _postId.value = postId
    }

    fun getPostId(): String? {
        return _postId.value
    }

    fun setCommentRegisterEvent(comment: String) {
        _commentRegisterEvent.value = Event(CommunityPostCommentRequest(comment))
    }

    fun setPostComment(accessToken: String, postId: String, content: CommunityPostCommentRequest) {
        viewModelScope.launch {
            val response =
                communityPostDetailRepository.setPostComment(accessToken, postId, content)
            if (response.isSuccessful) {
                Log.d("COMMUNITY/COMMENT/T", response.body().toString())
                getPostDetail(accessToken, postId)
            } else {
                Log.d("COMMUNITY/COMMENT/F", response.errorBody()?.string()!!)
            }
        }
    }

    fun getPostDetail(accessToken: String, postId: String) {
        viewModelScope.launch {
            val response = communityPostDetailRepository.getPostDetail(accessToken, postId)
            if (response.isSuccessful) {
                Log.d("COMMUNITY/DETAIL/T", response.body().toString())
                _communityPostDetailResponse.value = response.body()!!
            } else {
                Log.d("COMMUNITY/DETAIL/F", response.errorBody()?.string()!!)
            }
        }
    }

    fun setPostLike(accessToken: String, postId: String, like: Boolean) {
        viewModelScope.launch {
            val response =
                communityPostDetailRepository.setPostLike(accessToken, postId, CommunityPostLikeRequest(like))
            if (response.isSuccessful) {
                Log.d("COMMUNITY/LIKE/T", response.body().toString())
            } else {
                Log.d("COMMUNITY/LIKE/F", response.errorBody()?.string()!!)
            }
        }
    }
}