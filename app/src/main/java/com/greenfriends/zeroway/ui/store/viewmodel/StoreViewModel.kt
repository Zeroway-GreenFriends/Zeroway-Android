package com.greenfriends.zeroway.ui.store.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenfriends.zeroway.data.model.StoreResponse
import com.greenfriends.zeroway.data.repository.store.StoreRepository
import kotlinx.coroutines.launch

class StoreViewModel(private val storeRepository: StoreRepository) : ViewModel() {

    private val _stores = MutableLiveData<List<StoreResponse>>()
    val stores: LiveData<List<StoreResponse>> = _stores

    private val _keyword = MutableLiveData<String?>(null)
    val keyword: LiveData<String?> = _keyword

    private val _page = MutableLiveData<Int>(1)
    val page: LiveData<Int> = _page

    fun setKeyword(keyword: String?) {
        _keyword.value = keyword
    }

    fun getKeyword(): String? {
        return _keyword.value
    }

    fun setPage(page: Int) {
        _page.value = page
    }

    fun getPage(): Int? {
        return _page.value
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