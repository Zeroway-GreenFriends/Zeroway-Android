package com.greenfriends.zeroway.ui.community

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenfriends.zeroway.model.CommunityPost
import com.greenfriends.zeroway.repository.community.CommunityRepository
import kotlinx.coroutines.launch

class CommunityViewModel(private val communityRepository: CommunityRepository) : ViewModel() {

    private val _sort = MutableLiveData<String>("createdAt")
    val sort: LiveData<String> = _sort

    private val _communityPosts = MutableLiveData<List<CommunityPost>>()
    val communityPosts: LiveData<List<CommunityPost>> = _communityPosts

    fun setSort(sort: String) {
        _sort.value = sort
    }

    fun getSort(): String? {
        return _sort.value
    }

    fun getPosts(accessToken: String, sort: String) {
        viewModelScope.launch {
            val response = communityRepository.getPosts(accessToken, sort)
            Log.d("COMMUNITY/GET", response.body().toString())
            if (response.isSuccessful) {
                _communityPosts.value = response.body()!!.communityPosts
            } else {
                Log.d("COMMUNITY/GET/FAILURE", response.errorBody()?.string()!!)
            }
        }
    }
}