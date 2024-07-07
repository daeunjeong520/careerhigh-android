package com.daeun.careerhigh.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.databinding.ActivityClientLoginBinding

class ClientLoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityClientLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 로그인 화면 -> 프로필 생성 페이지
        binding.signUpButton.setOnClickListener {
            val intent = Intent(this@ClientLoginActivity, ClientProfileCreateActivity::class.java)
            startActivity(intent)
        }
    }
}