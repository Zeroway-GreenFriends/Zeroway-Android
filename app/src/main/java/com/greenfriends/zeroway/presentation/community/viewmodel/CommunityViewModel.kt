package com.greenfriends.zeroway.presentation.community.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenfriends.zeroway.data.model.CommunityLikeRequest
import com.greenfriends.zeroway.data.model.CommunityPost
import com.greenfriends.zeroway.data.model.CommunityPostBookmarkRequest
import com.greenfriends.zeroway.data.repository.community.CommunityRepository
import com.greenfriends.zeroway.presentation.common.Event
import kotlinx.coroutines.launch

class CommunityViewModel(private val communityRepository: CommunityRepository) : ViewModel() {

    private val _sort = MutableLiveData("createdAt")
    val sort: LiveData<String> = _sort

    private val _review = MutableLiveData(false)
    val review: LiveData<Boolean> = _review

    private val _challenge = MutableLiveData(false)
    val challenge: LiveData<Boolean> = _challenge

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _page = MutableLiveData<Long>(1)
    val page: LiveData<Long> = _page

    private val _communityPosts = MutableLiveData<List<CommunityPost>>()
    val communityPosts: LiveData<List<CommunityPost>> = _communityPosts

    private val _communityPostDetailEvent = MutableLiveData<Event<Long>>()
    val communityPostDetailEvent: LiveData<Event<Long>> = _communityPostDetailEvent

    fun setSort(sort: String) {
        _sort.value = sort
    }

    fun getSort(): String? {
        return _sort.value
    }

    fun setReview() {
        _review.value = !_review.value!!
    }

    fun getReview(): Boolean? {
        return _review.value
    }

    fun setChallenge() {
        _challenge.value = !_challenge.value!!
    }

    fun getChallenge(): Boolean? {
        return _challenge.value
    }

    fun setIsLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    fun getIsLoading(): Boolean? {
        return _isLoading.value
    }

    fun setPage(page: Long) {
        _page.value = page
    }

    fun getPage(): Long? {
        return _page.value
    }

    fun setCommunityPostDetailEvent(postId: Long) {
        _communityPostDetailEvent.value = Event(postId)
    }

    fun getPosts(
        accessToken: String,
        sort: String?,
        page: Long?,
        size: Long?,
        challenge: Boolean?,
        review: Boolean?
    ) {
        viewModelScope.launch {
            val response =
                communityRepository.getPosts(accessToken, sort, page, size, challenge, review)
            if (response.isSuccessful) {
                _communityPosts.value = response.body()!!.communityPosts
                Log.d("COMMUNITY/ALLPOST/T", response.body().toString())
            } else {
                Log.d("COMMUNITY/ALLPOST/F", response.errorBody()?.string()!!)
            }
        }
    }

    fun setPostLike(accessToken: String, postId: String, like: Boolean) {
        viewModelScope.launch {
            val response =
                communityRepository.setPostLike(accessToken, postId, CommunityLikeRequest(like))
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
                communityRepository.setPostBookmark(
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
}