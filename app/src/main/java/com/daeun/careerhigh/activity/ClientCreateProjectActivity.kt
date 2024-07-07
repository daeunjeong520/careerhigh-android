package com.daeun.careerhigh.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.databinding.ActivityClientCreateProjectBinding

class ClientCreateProjectActivity:AppCompatActivity() {

    private lateinit var binding: ActivityClientCreateProjectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientCreateProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}