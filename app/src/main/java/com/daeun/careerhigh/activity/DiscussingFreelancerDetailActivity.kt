package com.daeun.careerhigh.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.databinding.ActivityDiscussingFreelancerDetailBinding

/**
 * 협의 희망 상세 화면
 */
class DiscussingFreelancerDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDiscussingFreelancerDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiscussingFreelancerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}