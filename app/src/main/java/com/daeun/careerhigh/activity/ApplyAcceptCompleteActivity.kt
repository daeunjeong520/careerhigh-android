package com.daeun.careerhigh.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.databinding.ActivityAcceptApplyCompleteBinding

class ApplyAcceptCompleteActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAcceptApplyCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAcceptApplyCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientId = intent.getLongExtra("clientId", 1L)
        val freelancerId = intent.getLongExtra("freelancerId", 1L)
        val projectId = intent.getLongExtra("projectId", 1L)
        val freelancerProjectId = intent.getLongExtra("freelancerProjectId", 1L)
        val status = intent.getStringExtra("status")

        Log.e("ApplyAcceptCompleteActivity", "freelancerProjectId: ${freelancerProjectId}, status:${status}")

        binding.btnMyProject.setOnClickListener {
            val intent = Intent(this@ApplyAcceptCompleteActivity, ClientMyProjectActivity::class.java)
            intent.putExtra("clientId", clientId);
            startActivity(intent);
        }
    }
}