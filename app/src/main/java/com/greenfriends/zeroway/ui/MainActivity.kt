package com.greenfriends.zeroway.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.greenfriends.zeroway.R
import com.greenfriends.zeroway.databinding.ActivityMainBinding
import com.greenfriends.zeroway.ui.challenge.ChallengeCharacterFragment
import com.greenfriends.zeroway.ui.home.view.HomeFragment
import com.greenfriends.zeroway.ui.community.view.CommunityFragment
import com.greenfriends.zeroway.ui.mypage.MyPageFragment
import com.greenfriends.zeroway.ui.store.view.StoreFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fl, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fl, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.challengeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fl, ChallengeCharacterFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.storeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fl, StoreFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.communityFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fl, CommunityFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.myPageFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fl, MyPageFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}