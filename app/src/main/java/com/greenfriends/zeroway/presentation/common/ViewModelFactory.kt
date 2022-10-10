package com.greenfriends.zeroway.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.greenfriends.zeroway.data.repository.challenge.ChallengeRepository
import com.greenfriends.zeroway.data.repository.community.CommunityPostDetailRepository
import com.greenfriends.zeroway.data.repository.community.CommunityPostRegisterRepository
import com.greenfriends.zeroway.data.repository.community.CommunityRepository
import com.greenfriends.zeroway.data.repository.home.HomeRepository
import com.greenfriends.zeroway.data.repository.mypage.NoticeRepository
import com.greenfriends.zeroway.data.repository.signup.SignUpRepository
import com.greenfriends.zeroway.data.repository.store.StorePostDetailRepository
import com.greenfriends.zeroway.data.repository.store.StoreRepository
import com.greenfriends.zeroway.data.source.remote.challenge.ChallengeDataSourceImpl
import com.greenfriends.zeroway.data.source.remote.community.CommunityDataSourceImpl
import com.greenfriends.zeroway.data.source.remote.community.CommunityPostDetailDataSourceImpl
import com.greenfriends.zeroway.data.source.remote.community.CommunityPostRegisterDataSourceImpl
import com.greenfriends.zeroway.data.source.remote.home.HomeDataSourceImpl
import com.greenfriends.zeroway.data.source.remote.mypage.NoticeDataSourceImpl
import com.greenfriends.zeroway.data.source.remote.signup.SignUpDataSourceImpl
import com.greenfriends.zeroway.data.source.remote.store.StoreDataSourceImpl
import com.greenfriends.zeroway.data.source.remote.store.StorePostDetailDataSourceImpl
import com.greenfriends.zeroway.presentation.challenge.viewmodel.ChallengeViewModel
import com.greenfriends.zeroway.presentation.community.viewmodel.CommunityPostDetailViewModel
import com.greenfriends.zeroway.presentation.community.viewmodel.CommunityPostRegisterViewModel
import com.greenfriends.zeroway.presentation.community.viewmodel.CommunityViewModel
import com.greenfriends.zeroway.presentation.home.viewmodel.HomeViewModel
import com.greenfriends.zeroway.presentation.mypage.viewmodel.NoticeViewModel
import com.greenfriends.zeroway.presentation.signup.viewmodel.SignUpViewModel
import com.greenfriends.zeroway.presentation.store.viewmodel.StorePostDetailViewModel
import com.greenfriends.zeroway.presentation.store.viewmodel.StoreViewModel

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
            modelClass.isAssignableFrom(StorePostDetailViewModel::class.java) -> {
                StorePostDetailViewModel(
                    StorePostDetailRepository(
                        StorePostDetailDataSourceImpl()
                    )
                ) as T
            }
            modelClass.isAssignableFrom(NoticeViewModel::class.java) -> {
                NoticeViewModel(
                    NoticeRepository(
                        NoticeDataSourceImpl()
                    )
                ) as T
            }
            else -> {
                throw java.lang.IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}