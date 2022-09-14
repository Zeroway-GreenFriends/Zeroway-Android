package com.greenfriends.zeroway.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.greenfriends.zeroway.repository.challenge.ChallengeDataSourceImpl
import com.greenfriends.zeroway.repository.challenge.ChallengeRepository
import com.greenfriends.zeroway.repository.home.HomeDataSourceImpl
import com.greenfriends.zeroway.repository.home.HomeRepository
import com.greenfriends.zeroway.ui.challenge.ChallengeViewModel
import com.greenfriends.zeroway.ui.home.HomeViewModel
import com.greenfriends.zeroway.data.repository.community.CommunityPostDetailRepository
import com.greenfriends.zeroway.data.repository.community.CommunityPostRegisterRepository
import com.greenfriends.zeroway.data.repository.community.CommunityRepository
import com.greenfriends.zeroway.data.repository.signup.SignUpRepository
import com.greenfriends.zeroway.data.repository.store.StoreRepository
import com.greenfriends.zeroway.data.source.remote.community.CommunityDataSourceImpl
import com.greenfriends.zeroway.data.source.remote.community.CommunityPostDetailDataSourceImpl
import com.greenfriends.zeroway.data.source.remote.community.CommunityPostRegisterDataSourceImpl
import com.greenfriends.zeroway.data.source.remote.signup.SignUpDataSourceImpl
import com.greenfriends.zeroway.data.source.remote.store.StoreDataSourceImpl
import com.greenfriends.zeroway.ui.community.viewmodel.CommunityPostDetailViewModel
import com.greenfriends.zeroway.ui.community.viewmodel.CommunityPostRegisterViewModel
import com.greenfriends.zeroway.ui.community.viewmodel.CommunityViewModel
import com.greenfriends.zeroway.ui.signup.viewmodel.SignUpViewModel
import com.greenfriends.zeroway.ui.store.viewmodel.StoreViewModel

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
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(
                    HomeRepository(
                        HomeDataSourceImpl()
                    )
                ) as T
            }
            modelClass.isAssignableFrom(ChallengeViewModel::class.java) -> {
                ChallengeViewModel(
                    ChallengeRepository(
                        ChallengeDataSourceImpl()
                    )
                ) as T
            }
            modelClass.isAssignableFrom(StoreViewModel::class.java) -> {
                StoreViewModel(
                    StoreRepository(
                        StoreDataSourceImpl()
                    )
                ) as T
            }
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(
                    SignUpRepository(
                        SignUpDataSourceImpl()
                    )
                ) as T
            }
            else -> {
                throw java.lang.IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}