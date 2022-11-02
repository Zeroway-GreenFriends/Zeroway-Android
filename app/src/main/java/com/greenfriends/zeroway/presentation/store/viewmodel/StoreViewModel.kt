package com.greenfriends.zeroway.presentation.store.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenfriends.zeroway.data.model.StoreResponse
import com.greenfriends.zeroway.data.repository.store.StoreRepository
import com.greenfriends.zeroway.util.Event
import kotlinx.coroutines.launch

class StoreViewModel(private val storeRepository: StoreRepository) : ViewModel() {

    private val _stores = MutableLiveData<List<StoreResponse>>()
    val stores: LiveData<List<StoreResponse>> = _stores


    private var keyword: String? = null

    private var isLoading: Boolean = false

    private var page: Int = 1

    private val _storePostDetailEvent = MutableLiveData<Event<Long>>()
    val storePostDetailEvent: LiveData<Event<Long>> = _storePostDetailEvent

    fun setKeyword(keyword: String?) {
        this.keyword = keyword
    }

    fun getKeyword(): String? {
        return keyword
    }

    fun setLoading(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    fun getLoading(): Boolean {
        return isLoading
    }

    fun setPage(page: Int) {
        this.page = page
    }

    fun getPage(): Int {
        return page
    }

    fun setStorePostDetailEvent(storeId: Long) {
        _storePostDetailEvent.value = Event(storeId)
    }

    fun getStores(keyword: String?, page: Int, size: Int) {
        viewModelScope.launch {
            val response = storeRepository.getStores(keyword, page, size)
            if (response.isSuccessful) {
                _stores.value = response.body()
                Log.d("STORE/ALLPOST/T", response.body().toString())
            } else {
                Log.d("STORE/ALLPOST/F", response.errorBody()?.string()!!)
            }
        }
    }
}