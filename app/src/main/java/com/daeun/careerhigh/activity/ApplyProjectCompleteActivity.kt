package com.daeun.careerhigh.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.databinding.ActivityApplyProjectCompleteBinding

class ApplyProjectCompleteActivity: AppCompatActivity() {

    private lateinit var binding: ActivityApplyProjectCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplyProjectCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientId = intent.getLongExtra("clientId", 1L)
        val freelancerId = intent.getLongExtra("freelancerId", 1L)
        val projectId = intent.getLongExtra("projectId", 1L)
        val freelancerProjectId = intent.getLongExtra("freelancerProjectId", 1L)
        val status = intent.getStringExtra("status")

        Log.e("ApplyProjectCompleteActivity", "clientId: ${clientId}, freelancerId: ${freelancerId}, projectId: ${projectId}")
        Log.e("ApplyProjectCompleteActivity", "freelancerProjectId: ${freelancerProjectId}, status: ${status}")


        // 클라이언트 프로젝트 관리 페이지로 이동
        val intent = Intent(this@ApplyProjectCompleteActivity, ClientMyProjectActivity::class.java)
        intent.putExtra("clientId", clientId)
        startActivity(intent)
    }
}