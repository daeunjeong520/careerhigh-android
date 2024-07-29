package com.daeun.careerhigh.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.api.FreelancerService
import com.daeun.careerhigh.api.ProjectService
import com.daeun.careerhigh.databinding.ActivityDiscussingFreelancerDetailBinding
import com.daeun.careerhigh.vo.request.ProjectDiscussionStatusRequest
import com.daeun.careerhigh.vo.response.FreelancerDetail
import com.daeun.careerhigh.vo.response.ProjectDiscussionDetail
import com.daeun.careerhigh.vo.response.ProjectDiscussionStatusResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 협의 희망 상세 화면
 */
class DiscussingFreelancerDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDiscussingFreelancerDetailBinding
    private lateinit var uri: Uri

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiscussingFreelancerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientId = intent.getLongExtra("clientId", 1L)
        val freelancerId = intent.getLongExtra("freelancerId", 1L)
        val projectId = intent.getLongExtra("projectId", 1L)

        Log.e("DiscussingFreelancerDetailActivity", "clientId=${clientId}, freelancerId=${freelancerId}, projectId=${projectId}")

        // 프리랜서-프로젝트 상세 정보
        getDiscussionProjectDetail(freelancerId, projectId)

        // 계약 완료 버튼
        binding.btnAccept.setOnClickListener {
            if(!binding.checkBoxClient.isChecked) {
                Toast.makeText(this@DiscussingFreelancerDetailActivity, "클라이언트 계약 완료!", Toast.LENGTH_SHORT).show()
                binding.checkBoxClient.isChecked = true

                // 프로젝트 상태 => CREATE => DISCUSSION_ACCEPT
                changeDiscussionProjectStatus(freelancerId, projectId, "DISCUSSION_ACCEPT")

            }else {
                Toast.makeText(this@DiscussingFreelancerDetailActivity, "클라이언트 계약 해제", Toast.LENGTH_SHORT).show()
                binding.checkBoxClient.isChecked = false

                // 프로젝트 상태 => DISCUSSION_ACCEPT => CREATE
                changeDiscussionProjectStatus(freelancerId, projectId, "CREATE")

            }
        }
    }

    // TODO: 협의중인 프로젝트 상태 변경
    private fun changeDiscussionProjectStatus(freelancerId: Long, projectId: Long, status: String) {
        val projectService = retrofit.create(ProjectService::class.java)

        val request = ProjectDiscussionStatusRequest(projectId, freelancerId, status)

        projectService.changeDiscussionProjectStatus(request).enqueue(object: Callback<ProjectDiscussionStatusResponse> {
            override fun onResponse(call: Call<ProjectDiscussionStatusResponse>, response: Response<ProjectDiscussionStatusResponse>) {

                Log.e("DiscussingFreelancerDetailActivity", "clientStatus = ${response.body()?.clientStatus}")
                Log.e("DiscussingFreelancerDetailActivity", "freelancerStatus = ${response.body()?.freelancerStatus}")

                val freelancerStatus = response.body()?.freelancerStatus
                val clientStatus = response.body()?.clientStatus
                val clientId = response.body()?.clientId

                // 프리랜서-클라이언트 모두 계약 완료 => 진행중인 프로젝트로 이동
                if(freelancerStatus.equals("ONGOING") && clientStatus.equals("ONGOING")) {
                    Toast.makeText(this@DiscussingFreelancerDetailActivity, "계약이 완료되었습니다!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@DiscussingFreelancerDetailActivity, ProjectDiscussionCompleteActivity::class.java)
                    intent.putExtra("freelancerStatus", freelancerStatus)
                    intent.putExtra("clientStatus", clientStatus)
                    intent.putExtra("projectId", projectId)
                    intent.putExtra("freelancerId", freelancerId)
                    intent.putExtra("clientId", clientId)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<ProjectDiscussionStatusResponse>, t: Throwable) {
                Log.e("DiscussingFreelancerDetailActivity", t.toString())
            }
        })
    }


    // 프리랜서-프로젝트 상세
    private fun getDiscussionProjectDetail(freelancerId: Long, projectId: Long) {
        val projectService = retrofit.create(ProjectService::class.java)

        projectService.getDiscussionProjectDetail(projectId, freelancerId).enqueue(object: Callback<ProjectDiscussionDetail> {
            override fun onResponse(
                call: Call<ProjectDiscussionDetail>,
                response: Response<ProjectDiscussionDetail>
            ) {
                Log.e("DiscussingFreelancerDetailActivity", "${response.body().toString()}")
                uri = Uri.parse("android.resource://com.daeun.careerhigh/drawable/${response.body()?.freelancerImgPath}")
                binding.freelancerImage.setImageURI(uri)
                binding.tvFreelancer.text = response.body()?.freelancerName
                binding.tvClient.text = response.body()?.companyName


                binding.JobGroup.text = "${response.body()?.jobGroup} > "
                binding.Job.text = response.body()?.job
                binding.JobSkill.text = response.body()?.skill
                binding.Proficiency.text = response.body()?.proficiency
                binding.Career.text = "${response.body()?.careerYear}년 이상"
                binding.StartDate.text = response.body()?.startDate
                binding.StarRating.text = "${response.body()?.starRating}"

                binding.projectTitle.text = response.body()?.title
                binding.companyName.text = response.body()?.companyName
                binding.workStyle.text = response.body()?.workStyle
                binding.salary.text = "${response.body()?.salary}만원"
                response.body()?.description.also { binding.projectDescription.text }

                if(response.body()?.freelancerStatus.equals("DISCUSSION_ACCEPT")) {
                    binding.checkBoxFreelancer.isChecked = true
                }
            }

            override fun onFailure(call: Call<ProjectDiscussionDetail>, t: Throwable) {
                Log.e("DiscussingFreelancerDetailActivity", t.toString())
            }
        })
    }
}