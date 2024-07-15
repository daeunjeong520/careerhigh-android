package com.daeun.careerhigh.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.databinding.ActivityClientMyProjectBinding
import com.daeun.careerhigh.databinding.ActivityProjectCreateListBinding

// 내 프로젝트 관리 화면
class ClientMyProjectActivity:AppCompatActivity() {

    private lateinit var binding: ActivityClientMyProjectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientMyProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientId = intent.getLongExtra("clientId", 1L)
        val projectId = intent.getLongExtra("projectId", 1L)

        // 진행중인 프로젝트(ONGOING)

        // 등록한 프로젝트(CREATE)
        binding.btnUploadedProject.setOnClickListener {
            val intent = Intent(this@ClientMyProjectActivity, ProjectCreateListActivity::class.java)
            intent.putExtra("clientId", clientId)
            intent.putExtra("projectId", projectId)
            startActivity(intent);
        }

        // 완료한 프로젝트(COMPLETE)

        // 프로젝트 등록 버튼
        binding.projectUpload.setOnClickListener {
            val intent = Intent(this@ClientMyProjectActivity, ClientCreateProjectActivity::class.java)
            intent.putExtra("clientId", clientId)
            startActivity(intent)
        }
    }
}