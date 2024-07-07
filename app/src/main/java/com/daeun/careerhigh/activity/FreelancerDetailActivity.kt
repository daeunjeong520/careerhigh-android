package com.daeun.careerhigh.activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.api.FreelancerService
import com.daeun.careerhigh.databinding.ActivityFreelancerDetailBinding
import com.daeun.careerhigh.vo.response.FreelancerDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FreelancerDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityFreelancerDetailBinding
    private lateinit var uri: Uri

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFreelancerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientId = intent.getLongExtra("clientId", 1L)
        val freelancerId = intent.getLongExtra("freelancerId", 1L)

        Log.e("FreelancerDetailActivity", "clientId: ${clientId}")
        Log.e("FreelancerDetailActivity", "freelancerId: ${freelancerId}")

        getFreelancer(freelancerId)
    }

    // 프리랜서 상세 조회
    private fun getFreelancer(freelancerId: Long) {
        val freelancerService = retrofit.create(FreelancerService::class.java)

        freelancerService.getFreelancer(freelancerId).enqueue(object: Callback<FreelancerDetail> {
            override fun onResponse(call: Call<FreelancerDetail>, response: Response<FreelancerDetail>) {
                Log.e("FreelancerDetailActivity", "${response.body().toString()}")


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
                Log.e("FreelancerDetailActivity", t.toString())
            }
        })
    }
}