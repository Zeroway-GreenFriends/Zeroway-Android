package com.greenfriends.zeroway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.greenfriends.zeroway.databinding.ActivityLoginBinding
import com.greenfriends.zeroway.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        binding.loginKakaoLoginIv.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.loginGoogleLoginIv.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.loginNaverLoginIv.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        setContentView(binding.root)
    }
}