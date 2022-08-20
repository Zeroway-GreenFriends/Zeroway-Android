package com.greenfriends.zeroway.ui.challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.greenfriends.zeroway.R
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

        binding.levelupLevelTv.text = "Lv."+getLevel().toString()

        when(getLevel()){
            "2" ->{
                binding.levelupCharacterIv.setImageResource(R.drawable.ic_level_up_2)
            }
            "3" ->{
                binding.levelupCharacterIv.setImageResource(R.drawable.ic_level_up_3)
            }
            "4" ->{
                binding.levelupCharacterIv.setImageResource(R.drawable.ic_level_up_4)
            }
            "5" ->{
                binding.levelupCharacterIv.setImageResource(R.drawable.ic_level_up_5)
            }
            "6" ->{
                binding.levelupCharacterIv.setImageResource(R.drawable.ic_level_up_6)
            }
            "7" ->{
                binding.levelupCharacterIv.setImageResource(R.drawable.ic_level_up_7)
            }
            "8" ->{
                binding.levelupCharacterIv.setImageResource(R.drawable.ic_level_up_8)
            }
            "9" ->{
                binding.levelupCharacterIv.setImageResource(R.drawable.ic_level_up_9)
            }
            "10" ->{
                binding.levelupCharacterIv.setImageResource(R.drawable.ic_level_up_10)
            }
        }

        binding.levelupOkBtn.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }

    private fun getLevel(): String? {
        val sharedPreferences = getSharedPreferences("level", MODE_PRIVATE)
        return sharedPreferences!!.getString("level", null)
    }

}