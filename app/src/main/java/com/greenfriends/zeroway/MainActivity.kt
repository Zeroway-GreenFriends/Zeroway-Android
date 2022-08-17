package com.greenfriends.zeroway

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.greenfriends.zeroway.databinding.ActivityMainBinding
import com.greenfriends.zeroway.ui.ChallengeCharacterFragment
import com.greenfriends.zeroway.ui.home.HomeFragment
import com.greenfriends.zeroway.ui.store.StoreFragment
import com.greenfriends.zeroway.ui.UserFragment
import com.greenfriends.zeroway.ui.community.CommunityFragment

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

                R.id.userFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fl, UserFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}