package com.daeun.careerhigh.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.databinding.ActivityClientCreateProjectCompleteBinding

class ClientCreateProjectCompleteActivity:AppCompatActivity() {

    private lateinit var binding: ActivityClientCreateProjectCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientCreateProjectCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientId = intent.getLongExtra("clientId", 1L)
        val projectId = intent.getLongExtra("projectId", 1L)

        Log.e("CreateProjectCompleteActivity", "clientId: ${clientId}, projectId: ${projectId}")

        // 프로젝트 등록 완료 화면 => 프로젝트 관리 화면
        binding.btnProjectManage.setOnClickListener {
            val intent = Intent(this@ClientCreateProjectCompleteActivity, ClientMyProjectActivity::class.java)
            intent.putExtra("clientId", clientId)
            intent.putExtra("projectId", projectId)

            startActivity(intent)
        }
    }
}