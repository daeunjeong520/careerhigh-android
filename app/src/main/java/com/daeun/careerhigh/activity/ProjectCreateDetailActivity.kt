package com.daeun.careerhigh.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.api.ProjectService
import com.daeun.careerhigh.databinding.ActivityProjectCreateDetailBinding
import com.daeun.careerhigh.vo.response.ProjectCreateDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProjectCreateDetailActivity:AppCompatActivity() {

    private lateinit var binding: ActivityProjectCreateDetailBinding

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectCreateDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientId = intent.getLongExtra("clientId", 1L)
        val projectId = intent.getLongExtra("projectId", 1L)

        createProjectDetail(projectId)

        // 등록한 프로젝트 상세 화면 -> 지원한 프리랜서 목록
        binding.btnFreelancerApply.setOnClickListener {
            val intent = Intent(this@ProjectCreateDetailActivity, ApplyFreelancerListActivity::class.java)
            intent.putExtra("clientId", clientId)
            intent.putExtra("projectId", projectId)
            startActivity(intent)
        }

        // TODO: 등록한 프로젝트 상세 화면 -> 의뢰한 프리랜서 목록
        binding.btnFreelancerCommission.setOnClickListener {
            val intent = Intent(this@ProjectCreateDetailActivity, CommissionFreelancerListActivity::class.java)
            intent.putExtra("clientId", clientId)
            intent.putExtra("projectId", projectId)
            startActivity(intent)
        }

        // 메인화면
        binding.home.setOnClickListener {
            val intent = Intent(this@ProjectCreateDetailActivity, ClientMainActivity::class.java)
            intent.putExtra("clientId", clientId)
            startActivity(intent)
        }
    }

    private fun createProjectDetail(projectId: Long) {
        val projectService = retrofit.create(ProjectService::class.java)

        projectService.createProjectDetail(projectId).enqueue(object: Callback<ProjectCreateDetail> {
            override fun onResponse(call: Call<ProjectCreateDetail>, response: Response<ProjectCreateDetail>) {
                Log.e("ProjectCreateDetailActivity", "project = ${response.body().toString()}")

                binding.ProjectTitle.text = response.body()?.title
                binding.CompanyName.text = response.body()?.companyName
                binding.ProjectDetail.text = response.body()?.description
                binding.StartDate.text = response.body()?.startDate
                binding.period.text = "(${response.body()?.period}개월)"
                binding.JobGroup.text = "${response.body()?.jobGroup} > "
                binding.Job.text = response.body()?.job
                binding.JobSkill.text = response.body()?.skill
                binding.Salary.text = "${response.body()?.pay}만원 (월단위)"
                binding.WantCareerYear.text = "${response.body()?.wantCareerYear}년"
                binding.WorkStyle.text = response.body()?.workStyle

            }

            override fun onFailure(call: Call<ProjectCreateDetail>, t: Throwable) {
                Log.e("ProjectCreateDetailActivity", t.toString())
            }
        })
    }
}