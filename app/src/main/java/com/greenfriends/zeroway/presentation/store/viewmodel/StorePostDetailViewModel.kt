package com.greenfriends.zeroway.presentation.store.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenfriends.zeroway.data.model.StorePostDetailResponse
import com.greenfriends.zeroway.data.repository.store.StorePostDetailRepository
import kotlinx.coroutines.launch

class StorePostDetailViewModel(private val storePostDetailRepository: StorePostDetailRepository) :
    ViewModel() {

    private val _storeId = MutableLiveData<String>()
    val storeId: LiveData<String> = _storeId

    private val _storePostDetailResponse = MutableLiveData<StorePostDetailResponse>()
    val storePostDetailResponse: LiveData<StorePostDetailResponse> = _storePostDetailResponse

    fun setStoreId(storeId: String) {
        _storeId.value = storeId
    }

    fun getStoreDetail(storeId: String) {
        viewModelScope.launch {
            val response = storePostDetailRepository.getStoreDetail(storeId)
            if (response.isSuccessful) {
                Log.d("STORE/DETAIL/T", response.body().toString())
                _storePostDetailResponse.value = response.body()
            } else {
                Log.d("STORE/DETAIL/F", response.errorBody()?.string()!!)
            }
        }
    }
}