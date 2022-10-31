package com.greenfriends.zeroway.presentation.community.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenfriends.zeroway.data.model.CommunityLikeRequest
import com.greenfriends.zeroway.data.model.CommunityPostBookmarkRequest
import com.greenfriends.zeroway.data.model.CommunityPostCommentRequest
import com.greenfriends.zeroway.data.model.CommunityPostDetailResponse
import com.greenfriends.zeroway.data.repository.community.CommunityPostDetailRepository
import com.greenfriends.zeroway.presentation.common.Event
import kotlinx.coroutines.launch

class CommunityPostDetailViewModel(private val communityPostDetailRepository: CommunityPostDetailRepository) :
    ViewModel() {

    private val _postId = MutableLiveData<String>()
    val postId: LiveData<String>
        get() = _postId

    private val _communityPostDetailResponse = MutableLiveData<CommunityPostDetailResponse>()
    val communityPostDetailResponse: LiveData<CommunityPostDetailResponse>
        get() = _communityPostDetailResponse

    private val _commentRegisterEvent = MutableLiveData<Event<CommunityPostCommentRequest>>()
    val commentRegisterEvent: LiveData<Event<CommunityPostCommentRequest>>
        get() = _commentRegisterEvent

    private val _communityPostDetailDeleteEvent = MutableLiveData<Event<Boolean>>()
    val communityPostDetailDeleteEvent: LiveData<Event<Boolean>>
        get() = _communityPostDetailDeleteEvent

    private val _communityPostDetailCommentDeleteEvent = MutableLiveData<Event<Boolean>>()
    val communityPostDetailCommentDeleteEvent: LiveData<Event<Boolean>>
        get() = _communityPostDetailCommentDeleteEvent

    fun setPostId(postId: String) {
        _postId.value = postId
    }

    fun getPostId(): String? {
        return _postId.value
    }

    fun setCommentRegisterEvent(comment: String) {
        _commentRegisterEvent.value = Event(CommunityPostCommentRequest(comment))
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
                communityPostDetailRepository.setPostLike(
                    accessToken,
                    postId,
                    CommunityLikeRequest(like)
                )
            if (response.isSuccessful) {
                Log.d("COMMUNITY/LIKE/T", response.body().toString())
            } else {
                Log.d("COMMUNITY/LIKE/F", response.errorBody()?.string()!!)
            }
        }
    }

    fun setPostBookmark(accessToken: String, postId: String, bookmark: Boolean) {
        viewModelScope.launch {
            val response =
                communityPostDetailRepository.setPostBookmark(
                    accessToken,
                    postId,
                    CommunityPostBookmarkRequest(bookmark)
                )
            if (response.isSuccessful) {
                Log.d("COMMUNITY/BOOKMARK/T", response.body().toString())
            } else {
                Log.d("COMMUNITY/BOOKMARK/F", response.errorBody()?.string()!!)
            }
        }
    }

    fun deletePost(accessToken: String, postId: String) {
        viewModelScope.launch {
            val response = communityPostDetailRepository.deletePost(accessToken, postId)
            when {
                response.isSuccessful -> {
                    _communityPostDetailDeleteEvent.value = Event(true)
                    Log.d("COMMUNITY/DELETE/T", response.body().toString())
                }
                response.code() == 401 -> {
                    _communityPostDetailDeleteEvent.value = Event(false)
                }
                else -> {
                    Log.d("COMMUNITY/DELETE/F", response.errorBody()?.string()!!)
                }
            }
        }
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

    fun setPostCommentLike(accessToken: String, commentId: String, like: Boolean) {
        viewModelScope.launch {
            val response = communityPostDetailRepository.setPostCommentLike(
                accessToken,
                commentId,
                CommunityLikeRequest(like)
            )
            if (response.isSuccessful) {
                Log.d("COMMUNITY/LIKE/T", response.body().toString())
            } else {
                Log.d("COMMUNITY/LIKE/F", response.errorBody()?.string()!!)
            }
        }
    }

    fun deletePostComment(accessToken: String, commentId: String) {
        viewModelScope.launch {
            Log.d("SSS", "$accessToken $commentId")
            val response = communityPostDetailRepository.deletePostComment(
                accessToken,
                commentId
            )
            when {
                response.isSuccessful -> {
                    _communityPostDetailCommentDeleteEvent.value = Event(true)
                    Log.d("COMMUNITY/DELETE/T", response.body().toString())
                    getPostDetail(accessToken, getPostId()!!)
                }
                response.code() == 401 -> {
                    _communityPostDetailCommentDeleteEvent.value = Event(false)
                }
                else -> {
                    Log.d("COMMUNITY/DELETE/F", response.errorBody()?.string()!!)
                }
            }
        }
    }
}