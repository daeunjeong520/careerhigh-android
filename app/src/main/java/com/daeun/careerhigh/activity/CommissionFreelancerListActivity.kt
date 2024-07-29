package com.daeun.careerhigh.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.daeun.careerhigh.adapter.ApplyFreelancerAdapter
import com.daeun.careerhigh.adapter.FreelancerAdapter
import com.daeun.careerhigh.api.ProjectService
import com.daeun.careerhigh.databinding.ActivityCommissionFreelancerListBinding
import com.daeun.careerhigh.vo.response.FreelancerInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 의뢰한 프리랜서 목록(등록한 프로젝트 -> 프로젝트 선택 -> 지원한 프리랜서)
 */
class CommissionFreelancerListActivity: AppCompatActivity() {

    private lateinit var binding: ActivityCommissionFreelancerListBinding
    private lateinit var freelancerAdapter: ApplyFreelancerAdapter

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommissionFreelancerListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientId = intent.getLongExtra("clientId", 1L)
        val projectId = intent.getLongExtra("projectId", 1L)
        Log.e("CommissionFreelancerListActivity", "clientId: ${clientId}, projectId: ${projectId}")

        freelancerAdapter = ApplyFreelancerAdapter {
            val intent = Intent(this@CommissionFreelancerListActivity, CommissionFreelancerDetailActivity::class.java)
            intent.putExtra("freelancerId", it.freelancerId)
            intent.putExtra("clientId", clientId)
            intent.putExtra("projectId", projectId)
            startActivity(intent)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CommissionFreelancerListActivity)
            adapter = freelancerAdapter
        }

        commissionFreelancerList(projectId)

        binding.home.setOnClickListener {
            val intent = Intent(this@CommissionFreelancerListActivity, ClientMainActivity::class.java)
            intent.putExtra("clientId", clientId)
            startActivity(intent)
        }
    }

    /**
     * 의뢰한 프리랜서 목록
     */
    private fun commissionFreelancerList(projectId: Long) {
        val projectService = retrofit.create(ProjectService::class.java)

        projectService.getCommissionFreelancerList(projectId)
            .enqueue(object: Callback<List<FreelancerInfo>> {
                override fun onResponse(call: Call<List<FreelancerInfo>>, response: Response<List<FreelancerInfo>>) {
                    Log.e("CommissionFreelancerListActivity", "freelancerList = ${response.body().toString()}")
                    freelancerAdapter.submitList(response.body())
                }

                override fun onFailure(call: Call<List<FreelancerInfo>>, t: Throwable) {
                    Log.e("CommissionFreelancerListActivity", t.toString())
                }
            })
    }
}