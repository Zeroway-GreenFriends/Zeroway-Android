package com.greenfriends.zeroway.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.greenfriends.zeroway.repository.community.CommunityDataSourceImpl
import com.greenfriends.zeroway.repository.community.CommunityPostRegisterDataSourceImpl
import com.greenfriends.zeroway.repository.community.CommunityPostRegisterRepository
import com.greenfriends.zeroway.repository.community.CommunityRepository
import com.greenfriends.zeroway.ui.community.CommunityPostRegisterViewModel
import com.greenfriends.zeroway.ui.community.CommunityViewModel

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(CommunityPostRegisterViewModel::class.java) -> {
                return CommunityPostRegisterViewModel(
                    CommunityPostRegisterRepository(
                        CommunityPostRegisterDataSourceImpl()
                    )
                ) as T
            }
            modelClass.isAssignableFrom(CommunityViewModel::class.java) -> {
                return CommunityViewModel(CommunityRepository(CommunityDataSourceImpl())) as T
            }
            else -> {
                throw java.lang.IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}