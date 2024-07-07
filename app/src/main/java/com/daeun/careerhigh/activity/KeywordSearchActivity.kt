package com.daeun.careerhigh.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.databinding.ActivityKeywordSearchBinding

class KeywordSearchActivity: AppCompatActivity() {

    private lateinit var binding: ActivityKeywordSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKeywordSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientId:Long = intent.getLongExtra("clientId", 1L)
        var check:String = ""

        binding.radioGroup.setOnCheckedChangeListener { group, checkId ->
            when(checkId) {
                binding.radioFreelancer.id -> check = "freelancer"
                binding.radioProject.id -> check = "project"
            }
        }

        binding.btnSearch.setOnClickListener {
            var keyword:String = binding.Keyword.text.toString()

            if(check == "freelancer") {
                val intent = Intent(this@KeywordSearchActivity, SearchFreelancerResultActivity::class.java)
                intent.putExtra("clientId", clientId)
                intent.putExtra("keyword", keyword)
                startActivity(intent)
            }
        }
    }
}

