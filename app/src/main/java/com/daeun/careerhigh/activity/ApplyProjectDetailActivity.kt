package com.daeun.careerhigh.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.api.ProjectService
import com.daeun.careerhigh.databinding.ActivityApplyProjectDetailBinding
import com.daeun.careerhigh.vo.request.ProjectRequestRequest
import com.daeun.careerhigh.vo.response.ProjectCreateDetail
import com.daeun.careerhigh.vo.response.ProjectRequestResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplyProjectDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityApplyProjectDetailBinding

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplyProjectDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientId = intent.getLongExtra("clientId", 1L)
        val freelancerId = intent.getLongExtra("freelancerId", 1L)
        val projectId = intent.getLongExtra("projectId", 1L)

        Log.e("ApplyProjectDetailActivity", "clientId=${clientId}, freelancerId=${freelancerId}, projectId=${projectId}")
        createProjectDetail(projectId)

        binding.btnProjectApply.setOnClickListener {
            requestProject(projectId, freelancerId)
        }
    }

    private fun requestProject(projectId: Long, freelancerId: Long) {
        val projectService = retrofit.create(ProjectService::class.java)

        val request = ProjectRequestRequest(freelancerId, projectId)

        projectService.requestProject(request).enqueue(object: Callback<ProjectRequestResponse> {
            override fun onResponse(call: Call<ProjectRequestResponse>, response: Response<ProjectRequestResponse>) {
                Log.e("ApplyProjectDetailActivity", "project = ${response.body().toString()}")
                Toast.makeText(this@ApplyProjectDetailActivity, "프로젝트 의뢰가 완료되었습니다", Toast.LENGTH_SHORT).show()

                val clientId = response.body()?.clientId
                val freelancerId = response.body()?.freelancerId
                val projectId = response.body()?.projectId

                // 프로젝트 의뢰 완료화면으로 이동
                val intent = Intent(this@ApplyProjectDetailActivity, ApplyProjectCompleteActivity::class.java)
                intent.putExtra("clientId", clientId)
                intent.putExtra("freelancerId", freelancerId)
                intent.putExtra("projectId", projectId)
                startActivity(intent)
            }

            override fun onFailure(call: Call<ProjectRequestResponse>, t: Throwable) {
                Log.e("ApplyProjectDetailActivity", t.toString())
            }
        })

    }

    private fun createProjectDetail(projectId: Long) {
        val projectService = retrofit.create(ProjectService::class.java)

        projectService.createProjectDetail(projectId).enqueue(object: Callback<ProjectCreateDetail> {
            override fun onResponse(call: Call<ProjectCreateDetail>, response: Response<ProjectCreateDetail>) {
                Log.e("ApplyProjectDetailActivity", "project = ${response.body().toString()}")

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
                Log.e("ApplyProjectDetailActivity", t.toString())
            }
        })
    }
}