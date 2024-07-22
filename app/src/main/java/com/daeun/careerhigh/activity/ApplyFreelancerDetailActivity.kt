package com.daeun.careerhigh.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.api.FreelancerService
import com.daeun.careerhigh.api.ProjectService
import com.daeun.careerhigh.databinding.ActivityApplyFreelancerDetailBinding
import com.daeun.careerhigh.databinding.ActivityFreelancerDetailBinding
import com.daeun.careerhigh.vo.request.ProjectCommissionCancelRequest
import com.daeun.careerhigh.vo.request.ProjectDiscussionRequest
import com.daeun.careerhigh.vo.response.FreelancerDetail
import com.daeun.careerhigh.vo.response.ProjectCommissionCancelResponse
import com.daeun.careerhigh.vo.response.ProjectDiscussionResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 지원한 프리랜서 상세(프리랜서 상세 정보 + 협의 희망, 거절)
 */
class ApplyFreelancerDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityApplyFreelancerDetailBinding
    private lateinit var uri: Uri

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplyFreelancerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val freelancerId = intent.getLongExtra("freelancerId", 1L)
        val clientId = intent.getLongExtra("clientId", 1L)
        val projectId = intent.getLongExtra("projectId", 1L)

        Log.e("ApplyFreelancerDetailActivity", "freelancerId: ${freelancerId}, clientId=${clientId}, projectId=${projectId}")

        getFreelancer(freelancerId);

        // 협의 희망 => 협의 희망 완료 페이지
        binding.btnAccept.setOnClickListener {
            discussionProject(freelancerId, projectId, clientId);
        }

        // 지원 거절
        binding.btnDeny.setOnClickListener {
            cancelApplyProject(freelancerId, projectId, clientId)
        }

        binding.home.setOnClickListener {
            val intent = Intent(this@ApplyFreelancerDetailActivity, ClientMainActivity::class.java)
            intent.putExtra("clientId", clientId)
            startActivity(intent)
        }
    }


    // 지원한 프리랜서 상세 정보
    private fun getFreelancer(freelancerId: Long) {
        val freelancerService = retrofit.create(FreelancerService::class.java)

        freelancerService.getFreelancer(freelancerId).enqueue(object: Callback<FreelancerDetail> {
            override fun onResponse(call: Call<FreelancerDetail>, response: Response<FreelancerDetail>) {
                Log.e("ApplyFreelancerDetailActivity", "${response.body().toString()}")

                uri = Uri.parse("android.resource://com.daeun.careerhigh/drawable/${response.body()?.profileImg}")
                binding.profileImage.setImageURI(uri)
                binding.freelancerName.text = response.body()?.name
                binding.workStyle.text = response.body()?.workStyle
                binding.JobGroup.text = "${response.body()?.jobGroup} > "
                binding.Job.text = response.body()?.job
                binding.JobSkill.text = response.body()?.skill
                // TODO: 숙련도
                binding.Career.text = "${response.body()?.careerYear.toString()}년"
                //TODO: 시작 가능일
                binding.StarRating.text = response.body()?.starRating.toString()
                binding.description.text = response.body()?.description
                binding.Link.text = response.body()?.link
            }

            override fun onFailure(call: Call<FreelancerDetail>, t: Throwable) {
                Log.e("ApplyFreelancerDetailActivity", t.toString())
            }
        })
    }

    // 협의 희망
    private fun discussionProject(freelancerId: Long, projectId: Long, clientId: Long) {
        val projectService = retrofit.create(ProjectService::class.java)

        val request = ProjectDiscussionRequest(freelancerId, projectId)

        projectService.discussionProject(request).enqueue(object: Callback<ProjectDiscussionResponse> {
            override fun onResponse(call: Call<ProjectDiscussionResponse>, response: Response<ProjectDiscussionResponse>) {
                Log.e("ApplyFreelancerDetailActivity", "${response.body().toString()}")
                Toast.makeText(this@ApplyFreelancerDetailActivity, "프리랜서의 프로젝트 지원이 수락되었습니다.", Toast.LENGTH_SHORT).show()

                val freelancerProjectId = response.body()?.freelancerProjectId
                val status = response.body()?.status

                // 프로젝트 협의 희망 완료 페이지로 이동
                val intent = Intent(this@ApplyFreelancerDetailActivity,ApplyAcceptCompleteActivity::class.java)
                intent.putExtra("clientId", clientId)
                intent.putExtra("freelancerId", freelancerId)
                intent.putExtra("projectId", projectId)
                intent.putExtra("freelancerProjectId", freelancerProjectId)
                intent.putExtra("status", status)
                startActivity(intent)

            }

            override fun onFailure(call: Call<ProjectDiscussionResponse>, t: Throwable) {
                Log.e("ApplyFreelancerDetailActivity", t.toString())
            }
        })
    }

    // TODO: 지원 거절
    private fun cancelApplyProject(freelancerId: Long, projectId: Long, clientId: Long) {
        val projectService = retrofit.create(ProjectService::class.java)

        val request = ProjectCommissionCancelRequest(freelancerId, projectId);
        projectService.cancelCommissionProject(request).enqueue(object: Callback<ProjectCommissionCancelResponse> {
            override fun onResponse(call: Call<ProjectCommissionCancelResponse>, response: Response<ProjectCommissionCancelResponse>) {
                val result = response.body()?.result
                Log.e("ApplyFreelancerDetailActivity", "result = ${result}")
                Toast.makeText(this@ApplyFreelancerDetailActivity, "지원을 거절하였습니다.", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@ApplyFreelancerDetailActivity, ApplyFreelancerListActivity::class.java)
                intent.putExtra("clientId", clientId)
                intent.putExtra("projectId", projectId)

                startActivity(intent)
            }

            override fun onFailure(call: Call<ProjectCommissionCancelResponse>, t: Throwable) {
                Log.e("ApplyFreelancerDetailActivity", t.toString())
            }
        })
    }

}