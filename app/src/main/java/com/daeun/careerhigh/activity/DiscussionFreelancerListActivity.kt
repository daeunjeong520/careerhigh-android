package com.daeun.careerhigh.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.daeun.careerhigh.adapter.ApplyFreelancerAdapter
import com.daeun.careerhigh.api.ProjectService
import com.daeun.careerhigh.databinding.ActivityDiscussionFreelancerListBinding
import com.daeun.careerhigh.vo.response.FreelancerInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 협의 중인 프리랜서 목록
 */
class DiscussionFreelancerListActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDiscussionFreelancerListBinding
    private lateinit var freelancerAdapter: ApplyFreelancerAdapter

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiscussionFreelancerListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientId = intent.getLongExtra("clientId", 1L)
        val projectId = intent.getLongExtra("projectId", 1L)
        Log.e("DiscussionFreelancerListActivity", "clientId: ${clientId}, projectId: ${projectId}")

        freelancerAdapter = ApplyFreelancerAdapter {
            // TODO: 협의중인 프리랜서 상세
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DiscussionFreelancerListActivity)
            adapter = freelancerAdapter
        }

        discussionFreelancerList(projectId)

    }

    private fun discussionFreelancerList(projectId: Long) {
        val projectService = retrofit.create(ProjectService::class.java)

        projectService.discussionFreelancerList(projectId)
            .enqueue(object: Callback<List<FreelancerInfo>> {
                override fun onResponse(call: Call<List<FreelancerInfo>>, response: Response<List<FreelancerInfo>>) {
                    Log.e("DiscussionFreelancerListActivity", "freelancerList = ${response.body().toString()}")
                    freelancerAdapter.submitList(response.body())
                }

                override fun onFailure(call: Call<List<FreelancerInfo>>, t: Throwable) {
                    Log.e("DiscussionFreelancerListActivity", t.toString())
                }
            })
    }
}