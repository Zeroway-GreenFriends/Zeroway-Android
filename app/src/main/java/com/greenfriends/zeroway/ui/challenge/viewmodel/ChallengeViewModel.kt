package com.greenfriends.zeroway.ui.challenge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenfriends.zeroway.data.model.ChallengeLevelResponse
import com.greenfriends.zeroway.data.model.ChallengeListResponse
import com.greenfriends.zeroway.data.model.ChallengeResponse
import com.greenfriends.zeroway.data.repository.challenge.ChallengeRepository
import com.greenfriends.zeroway.ui.common.Event
import kotlinx.coroutines.launch

class ChallengeViewModel (private val challengeRepository: ChallengeRepository) : ViewModel() {

    private val _cr = MutableLiveData<ChallengeResponse>()
    val cr: LiveData<ChallengeResponse> = _cr

    private val _challengeList = MutableLiveData<List<ChallengeListResponse>>()
    val challengeList: LiveData<List<ChallengeListResponse>> = _challengeList

    private val _challengeLevelResponse = MutableLiveData<ChallengeLevelResponse>()
    val challengeLevelResponse: LiveData<ChallengeLevelResponse> = _challengeLevelResponse

    private val _updateEvent = MutableLiveData<Event<Boolean>>()
    val updateEvent : LiveData<Event<Boolean>> = _updateEvent

    fun getUserChallenge(accessToken: String) {
        viewModelScope.launch {
            val response = challengeRepository.getUserChallenge(accessToken)
            if (response.isSuccessful) {
                _cr.value = response.body()!!

                Log.d("CL/VM/S", response.body().toString())
            } else {
                Log.d("CL/VM/F", response.errorBody()?.string()!!)
            }
        }
    }

    fun getChallengeList(accessToken: String) {
        viewModelScope.launch {
            val response = challengeRepository.getChallengeList(accessToken)
            if (response.isSuccessful) {
                _challengeList.value = response.body()!!

                Log.d("CL-list/VM/S", response.body().toString())
            } else {
                Log.d("CL-list/VM/F", response.errorBody()?.string()!!)
            }
        }
    }

    fun updateChallenge(accessToken: String,challengeId: Long) {
        viewModelScope.launch {
            val response = challengeRepository.updateChallenge(accessToken, challengeId)
            if (response.isSuccessful) {
                _challengeLevelResponse.value = response.body()!!
                _updateEvent.value = Event(true)
                Log.d("CL-LV/VM/S", response.body().toString())
            } else {
                Log.d("CL-LV/VM/F", response.errorBody()?.string()!!)
            }
        }
    }

}