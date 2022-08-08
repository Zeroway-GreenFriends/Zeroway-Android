package com.greenfriends.zeroway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.greenfriends.zeroway.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)

        binding.signupStartBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        setContentView(binding.root)
    }
}