package com.daeun.careerhigh.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.daeun.careerhigh.adapter.ProjectAdapter
import com.daeun.careerhigh.api.ProjectService
import com.daeun.careerhigh.databinding.ActivityProjectCreateListBinding
import com.daeun.careerhigh.vo.response.ProjectInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProjectCreateListActivity:AppCompatActivity() {

    private lateinit var binding: ActivityProjectCreateListBinding
    private lateinit var projectAdapter: ProjectAdapter

    // retrofit 객체 생성
    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectCreateListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientId = intent.getLongExtra("clientId", 1L)

        projectAdapter = ProjectAdapter {

        }

        binding.projectRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = projectAdapter
        }

        val status = "CREATE"
        createProjectList(clientId, status)
    }

    private fun createProjectList(clientId: Long, status: String) {
        val projectService = retrofit.create(ProjectService::class.java)

        projectService.createProjectList(clientId, status).enqueue(object: Callback<List<ProjectInfo>> {
            override fun onResponse(call: Call<List<ProjectInfo>>, response: Response<List<ProjectInfo>>) {
                Log.e("ProjectCreateListActivity", "project List=${response.body().toString()}")

                projectAdapter.submitList(response.body())
            }

            override fun onFailure(call: Call<List<ProjectInfo>>, t: Throwable) {
                Log.e("ProjectCreateListActivity", t.toString())
            }
        })
    }
}