package com.daeun.careerhigh.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.databinding.ActivityClientProfileCreateBinding

class ClientProfileCreateActivity: AppCompatActivity() {

    private lateinit var binding: ActivityClientProfileCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientProfileCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var companyName:String = ""
        var managerName:String = ""
        var managerPhone:String = ""

        // 프로필 생성 -> 회원가입 페이지
        binding.btnComplete.setOnClickListener {
            companyName = binding.CompanyName.text.toString()
            managerName = binding.Name.text.toString()
            managerPhone = binding.PhoneNumber.text.toString()

            val intent = Intent(this@ClientProfileCreateActivity, ClientSignupActivity::class.java)
            intent.putExtra("companyName", companyName)
            intent.putExtra("managerName", managerName)
            intent.putExtra("managerPhone", managerPhone)

            startActivity(intent)
        }
    }
}