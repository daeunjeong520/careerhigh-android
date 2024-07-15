package com.daeun.careerhigh.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.databinding.ActivityClientSignupCompleteBinding

class ClientSignupCompleteActivity: AppCompatActivity() {

    private lateinit var binding: ActivityClientSignupCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientSignupCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // intent 데이터 꺼내오기
        val clientId = intent.getLongExtra("clientId", 1L)
        val name = intent.getStringExtra("name")

        Log.e("ClientSignupCompleteActivity", "clientId: ${clientId}, name: ${name}")

        binding.clientNameText.text = name


        // 로그인 페이지로 이동
        binding.btnStart.setOnClickListener {
            val intent = Intent(this@ClientSignupCompleteActivity, ClientLoginActivity::class.java)
            startActivity(intent)
        }
    }
}