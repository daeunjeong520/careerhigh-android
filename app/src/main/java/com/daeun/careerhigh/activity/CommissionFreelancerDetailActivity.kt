package com.daeun.careerhigh.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.api.FreelancerService
import com.daeun.careerhigh.api.ProjectService
import com.daeun.careerhigh.databinding.ActivityCommissionFreelancerDetailBinding
import com.daeun.careerhigh.vo.request.ProjectCommissionCancelRequest
import com.daeun.careerhigh.vo.response.FreelancerDetail
import com.daeun.careerhigh.vo.response.ProjectCommissionCancelResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 의뢰한 프리랜서 상세 (프리랜서 정보 + 의뢰 취소)
 */
class CommissionFreelancerDetailActivity:AppCompatActivity() {

    private lateinit var binding: ActivityCommissionFreelancerDetailBinding
    private lateinit var uri: Uri

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommissionFreelancerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val freelancerId = intent.getLongExtra("freelancerId", 1L)
        val clientId = intent.getLongExtra("clientId", 1L)
        val projectId = intent.getLongExtra("projectId", 1L)

        Log.e("CommissionFreelancerDetailActivity", "freelancerId: ${freelancerId}, clientId=${clientId}, projectId=${projectId}")

        getFreelancer(freelancerId);

        // 의뢰 취소
        binding.btnCommissionCancel.setOnClickListener {
            cancelCommissionProject(freelancerId, projectId, clientId)
        }

        binding.home.setOnClickListener {
            val intent = Intent(this@CommissionFreelancerDetailActivity, ClientMainActivity::class.java)
            intent.putExtra("clientId", clientId)
            startActivity(intent)
        }
    }

    private fun getFreelancer(freelancerId: Long) {
        val freelancerService = retrofit.create(FreelancerService::class.java)

        freelancerService.getFreelancer(freelancerId).enqueue(object: Callback<FreelancerDetail> {
            override fun onResponse(call: Call<FreelancerDetail>, response: Response<FreelancerDetail>) {
                Log.e("CommissionFreelancerDetailActivity", "${response.body().toString()}")

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
                Log.e("CommissionFreelancerDetailActivity", t.toString())
            }
        })
    }

    /**
     * 의뢰 취소
     */
    private fun cancelCommissionProject(freelancerId: Long, projectId: Long, clientId: Long) {
        val projectService = retrofit.create(ProjectService::class.java)

        val request = ProjectCommissionCancelRequest(freelancerId, projectId);
        projectService.cancelCommissionProject(request).enqueue(object: Callback<ProjectCommissionCancelResponse> {
            override fun onResponse(call: Call<ProjectCommissionCancelResponse>, response: Response<ProjectCommissionCancelResponse>) {
                val result = response.body()?.result
                Log.e("CommissionFreelancerDetailActivity", "result = ${result}")
                Toast.makeText(this@CommissionFreelancerDetailActivity, "의뢰 취소가 완료되었습니다.", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@CommissionFreelancerDetailActivity, CommissionFreelancerListActivity::class.java)
                intent.putExtra("clientId", clientId)
                intent.putExtra("projectId", projectId)

                startActivity(intent)
            }

            override fun onFailure(call: Call<ProjectCommissionCancelResponse>, t: Throwable) {
                Log.e("CommissionFreelancerDetailActivity", t.toString())
            }
        })
    }

}