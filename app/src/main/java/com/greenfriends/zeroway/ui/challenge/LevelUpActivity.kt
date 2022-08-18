package com.greenfriends.zeroway.ui.challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.greenfriends.zeroway.databinding.ActivityLevelupBinding
import com.greenfriends.zeroway.databinding.ActivityMainBinding
import com.greenfriends.zeroway.ui.challenge.ChallengeCharacterFragment
import com.greenfriends.zeroway.ui.home.HomeFragment
import com.greenfriends.zeroway.ui.store.StoreFragment
import com.greenfriends.zeroway.ui.UserFragment
import com.greenfriends.zeroway.ui.community.CommunityFragment

class LevelUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLevelupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelupBinding.inflate(layoutInflater)


        setContentView(binding.root)
    }

}