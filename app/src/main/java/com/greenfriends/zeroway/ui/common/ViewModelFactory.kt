package com.greenfriends.zeroway.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.greenfriends.zeroway.repository.community.CommunityDataSourceImpl
import com.greenfriends.zeroway.repository.community.CommunityRepository
import com.greenfriends.zeroway.repository.communitypostdetail.CommunityPostDetailDataSourceImpl
import com.greenfriends.zeroway.repository.communitypostdetail.CommunityPostDetailRepository
import com.greenfriends.zeroway.repository.communitypostregister.CommunityPostRegisterDataSourceImpl
import com.greenfriends.zeroway.repository.communitypostregister.CommunityPostRegisterRepository
import com.greenfriends.zeroway.ui.community.CommunityViewModel
import com.greenfriends.zeroway.ui.communitypostdetail.CommunityPostDetailViewModel
import com.greenfriends.zeroway.ui.communitypostregister.CommunityPostRegisterViewModel

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CommunityViewModel::class.java) -> {
                CommunityViewModel(CommunityRepository(CommunityDataSourceImpl())) as T
            }
            modelClass.isAssignableFrom(CommunityPostRegisterViewModel::class.java) -> {
                CommunityPostRegisterViewModel(
                    CommunityPostRegisterRepository(
                        CommunityPostRegisterDataSourceImpl()
                    )
                ) as T
            }
            modelClass.isAssignableFrom(CommunityPostDetailViewModel::class.java) -> {
                CommunityPostDetailViewModel(
                    CommunityPostDetailRepository(
                        CommunityPostDetailDataSourceImpl()
                    )
                ) as T
            }
            else -> {
                throw java.lang.IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}