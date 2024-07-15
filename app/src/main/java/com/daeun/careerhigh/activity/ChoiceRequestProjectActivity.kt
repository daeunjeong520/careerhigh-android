package com.daeun.careerhigh.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.daeun.careerhigh.adapter.ProjectAdapter
import com.daeun.careerhigh.api.ProjectService
import com.daeun.careerhigh.databinding.ActivityApplyProjectDetailBinding
import com.daeun.careerhigh.databinding.ActivityChoiceRequestProjectBinding
import com.daeun.careerhigh.vo.response.ProjectInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChoiceRequestProjectActivity: AppCompatActivity() {

    private lateinit var binding: ActivityChoiceRequestProjectBinding
    private lateinit var projectAdapter: ProjectAdapter

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoiceRequestProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientId = intent.getLongExtra("clientId", 1L)
        val freelancerId = intent.getLongExtra("freelancerId", 1L)

        Log.e("ChoiceRequestProjectActivity", "clientId: ${clientId}")
        Log.e("ChoiceRequestProjectActivity", "freelancerId: ${freelancerId}")

        projectAdapter = ProjectAdapter {
            val intent = Intent(this@ChoiceRequestProjectActivity, ApplyProjectDetailActivity::class.java)
            intent.putExtra("clientId", clientId)
            intent.putExtra("freelancerId", freelancerId)
            intent.putExtra("projectId", it.projectId)
            startActivity(intent)
        }

        binding.projectRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = projectAdapter
        }

        createProjectList(clientId, "CREATE")
    }

    private fun createProjectList(clientId: Long, status: String) {
        val projectService = retrofit.create(ProjectService::class.java)

        projectService.createProjectList(clientId, status).enqueue(object:
            Callback<List<ProjectInfo>> {
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