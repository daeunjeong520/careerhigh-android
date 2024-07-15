package com.daeun.careerhigh.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.databinding.ActivityApplyFreelancerListBinding

class ApplyFreelancerListActivity: AppCompatActivity() {

    private lateinit var binding: ActivityApplyFreelancerListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplyFreelancerListBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}