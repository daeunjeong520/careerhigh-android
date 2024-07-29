package com.daeun.careerhigh.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.databinding.ActivityProjectDiscussionCompleteBinding

/**
 * 협의 완료
 */
class ProjectDiscussionCompleteActivity: AppCompatActivity() {

    private lateinit var binding: ActivityProjectDiscussionCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectDiscussionCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}