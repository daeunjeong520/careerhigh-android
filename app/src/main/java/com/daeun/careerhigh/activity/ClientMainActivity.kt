package com.daeun.careerhigh.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.daeun.careerhigh.adapter.FreelancerAdapter
import com.daeun.careerhigh.api.FreelancerService
import com.daeun.careerhigh.databinding.ActivityClientMainBinding
import com.daeun.careerhigh.vo.response.FreelancerInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientMainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityClientMainBinding
    private lateinit var recommendedFreelancerAdapter: FreelancerAdapter

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientId = intent.getLongExtra("clientId", 1L)
        Log.e("ClientMainActivity", "clientId: ${clientId}")

        // 메인 화면 -> 키워드 검색 화면
        binding.btnKeywordSearch.setOnClickListener {
            val intent = Intent(this@ClientMainActivity, KeywordSearchActivity::class.java)
            intent.putExtra("clientId", clientId)
            startActivity(intent)
        }


        // 추천 프리랜서 목록
        recommendedFreelancerAdapter = FreelancerAdapter {
            // onClick (프리랜서 상세 페이지)
        }
        binding.recommendedFreelancerRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recommendedFreelancerAdapter
        }
        getAllFreelancers()


        // 메인 화면 -> 프로젝트 등록 화면
//        binding..setOnClickListener {
//            val intent = Intent(this@ClientMainActivity, ClientCreateProjectActivity::class.java)
//            intent.putExtra("clientId", clientId)
//            startActivity(intent)
//        }
    }

    private fun getAllFreelancers() {
        val freelancerService = retrofit.create(FreelancerService::class.java)

        freelancerService.getAllFreelancers().enqueue(object: Callback<List<FreelancerInfo>> {
            override fun onResponse(call: Call<List<FreelancerInfo>>, response: Response<List<FreelancerInfo>>) {
                Log.e("ClientMainActivity", "Get All Freelancers: ${response.body().toString()}")

                recommendedFreelancerAdapter.submitList(response.body())
            }

            override fun onFailure(call: Call<List<FreelancerInfo>>, t: Throwable) {
                Log.e("ClientMainActivity", "Error")
            }
        })
    }
}