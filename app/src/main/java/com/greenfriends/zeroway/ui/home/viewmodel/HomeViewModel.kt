package com.greenfriends.zeroway.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenfriends.zeroway.data.model.ChallengeResponse
import com.greenfriends.zeroway.data.model.TermResponse
import com.greenfriends.zeroway.data.model.TipResponse
import com.greenfriends.zeroway.data.repository.home.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel (private val homeRepository: HomeRepository) : ViewModel() {


    private val _cr = MutableLiveData<ChallengeResponse>()
    val cr: LiveData<ChallengeResponse> = _cr

    private val _tr = MutableLiveData<List<TermResponse>>()
    val tr: LiveData<List<TermResponse>> = _tr

    private val _tipResponse = MutableLiveData<List<TipResponse>>()
    val tipResponse: LiveData<List<TipResponse>> = _tipResponse

    private val _termSearchResponse = MutableLiveData<List<TermResponse>>()
    val termSearchResponse: LiveData<List<TermResponse>> = _termSearchResponse

    fun getUserChallenge(accessToken: String) {
        viewModelScope.launch {
            val response = homeRepository.getUserChallenge(accessToken)
            if (response.isSuccessful) {
                _cr.value = response.body()!!

                Log.d("HOME/VM/S", response.body().toString())
            } else {
                Log.d("HOME/VM/F", response.errorBody()?.string()!!)
            }
        }
    }

    fun getTerm(keyword: String?, page: Int?, size: Int?) {
        viewModelScope.launch {
            val response = homeRepository.getTerm(keyword,page,size)
            if (response.isSuccessful) {
                _tr.value = response.body()!!

                Log.d("HOME/TR/S", response.body().toString())
            } else {
                Log.d("HOME/TR/F", response.errorBody()?.string()!!)
            }
        }
    }

    fun getTip() {
        viewModelScope.launch {
            val response = homeRepository.getTip()
            if (response.isSuccessful) {
                _tipResponse.value = response.body()!!

                Log.d("HOME/TipR/S", response.body().toString())
            } else {
                Log.d("HOME/TipR/F", response.errorBody()?.string()!!)
            }
        }
    }

    fun getTermSearch(keyword: String?, page: Int?, size: Int?) {
        viewModelScope.launch {
            val response = homeRepository.getTermSearch(keyword,page,size)
            if (response.isSuccessful) {
                _termSearchResponse.value = response.body()!!

                Log.d("HOME/TermS/S", response.body().toString())
            } else {
                Log.d("HOME/TermS/F", response.errorBody()?.string()!!)
            }
        }
    }


}