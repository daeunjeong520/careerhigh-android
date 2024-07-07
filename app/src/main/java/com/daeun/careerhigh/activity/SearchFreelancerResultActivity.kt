package com.daeun.careerhigh.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.daeun.careerhigh.adapter.FreelancerAdapter
import com.daeun.careerhigh.api.FreelancerService
import com.daeun.careerhigh.databinding.ActivitySearchFreelancerResultBinding
import com.daeun.careerhigh.vo.response.FreelancerInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchFreelancerResultActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySearchFreelancerResultBinding
    private lateinit var freelancerAdapter: FreelancerAdapter

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchFreelancerResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientId = intent.getLongExtra("clientId", 1L)
        val keyword = intent.getStringExtra("keyword")
        Log.e("SearchResultActivity", "clientId: ${clientId}")

        freelancerAdapter = FreelancerAdapter {
            // TODO
        }

        binding.searchResultDescription.text = "'${keyword}'에 대한 검색 결과"
        binding.searchResultRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchFreelancerResultActivity)
            adapter = freelancerAdapter
        }

        if (keyword != null) {
            searchFreelancersByJob(keyword)
        }else {
            val intent = Intent(this@SearchFreelancerResultActivity, KeywordSearchActivity::class.java)
            intent.putExtra("clientId", clientId)
            startActivity(intent)
        }
    }

    // 프리랜서 검색 결과
    private fun searchFreelancersByJob(job: String) {
        val freelancerService = retrofit.create(FreelancerService::class.java)

        // 프리랜서 검색 결과 리스트 반환
        freelancerService.searchFreelancersByJob(job).enqueue(object: Callback<List<FreelancerInfo>> {
            override fun onResponse(call: Call<List<FreelancerInfo>>, response: Response<List<FreelancerInfo>>) {
                Log.e("SearchFreelancerResultActivity", "List Freelancers: ${response.body().toString()}")

                freelancerAdapter.submitList(response.body())
            }

            override fun onFailure(call: Call<List<FreelancerInfo>>, t: Throwable) {
                Log.e("SearchFreelancerResultActivity", t.toString())
            }
        })
    }
}