package com.greenfriends.zeroway.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.greenfriends.zeroway.repository.challenge.ChallengeDataSourceImpl
import com.greenfriends.zeroway.repository.challenge.ChallengeRepository
<<<<<<< HEAD
import com.greenfriends.zeroway.data.source.remote.home.HomeDataSourceImpl
=======
import com.greenfriends.zeroway.repository.community.CommunityDataSourceImpl
import com.greenfriends.zeroway.repository.community.CommunityRepository
import com.greenfriends.zeroway.repository.communitypostdetail.CommunityPostDetailDataSourceImpl
import com.greenfriends.zeroway.repository.communitypostdetail.CommunityPostDetailRepository
import com.greenfriends.zeroway.repository.communitypostregister.CommunityPostRegisterDataSourceImpl
import com.greenfriends.zeroway.repository.communitypostregister.CommunityPostRegisterRepository
import com.greenfriends.zeroway.repository.home.HomeDataSourceImpl
>>>>>>> parent of 39dddb6 (fix: 패키지 구조 반영)
import com.greenfriends.zeroway.repository.home.HomeRepository
import com.greenfriends.zeroway.ui.challenge.ChallengeViewModel
import com.greenfriends.zeroway.ui.community.CommunityViewModel
import com.greenfriends.zeroway.ui.communitypostdetail.CommunityPostDetailViewModel
import com.greenfriends.zeroway.ui.communitypostregister.CommunityPostRegisterViewModel
import com.greenfriends.zeroway.ui.home.HomeViewModel

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
<<<<<<< HEAD

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
=======
>>>>>>> parent of 39dddb6 (fix: 패키지 구조 반영)
            else -> {
                throw java.lang.IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}