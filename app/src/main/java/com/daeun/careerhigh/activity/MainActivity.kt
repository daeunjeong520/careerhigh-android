package com.daeun.careerhigh.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daeun.careerhigh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 클라이언트 버튼 클릭
        binding.clientButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ClientLoginActivity::class.java)
            startActivity(intent)
        }
    }
}