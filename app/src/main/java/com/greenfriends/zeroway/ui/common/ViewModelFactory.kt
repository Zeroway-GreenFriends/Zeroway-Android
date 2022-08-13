package com.greenfriends.zeroway.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.greenfriends.zeroway.repository.community.CommunityPostRegisterDataSourceImpl
import com.greenfriends.zeroway.repository.community.CommunityPostRegisterRepository
import com.greenfriends.zeroway.ui.community.CommunityPostRegisterViewModel

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommunityPostRegisterViewModel::class.java)) {
            return CommunityPostRegisterViewModel(
                CommunityPostRegisterRepository(
                    CommunityPostRegisterDataSourceImpl()
                )
            ) as T
        } else {
            throw java.lang.IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}