package com.daeun.careerhigh.activity

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.daeun.careerhigh.R
import com.daeun.careerhigh.api.ProjectService
import com.daeun.careerhigh.databinding.ActivityClientCreateProjectBinding
import com.daeun.careerhigh.vo.request.ProjectCreateRequest
import com.daeun.careerhigh.vo.response.ProjectCreateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate

class ClientCreateProjectActivity:AppCompatActivity() {

    private lateinit var binding: ActivityClientCreateProjectBinding

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientCreateProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientId = intent.getLongExtra("clientId", 1L);
        var title = ""
        var description = ""
        var jobGroup = ""
        var job = ""
        var workStyle = ""
        var startDate = ""
        var period = 0
        var pay = 0
        var skill = ""
        var wantCareer = 0

        //스피너
        binding.spinnerJobGroup.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.job_group,
            android.R.layout.simple_list_item_1
        )
        binding.spinnerWorkStyle.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.work_style,
            android.R.layout.simple_list_item_1
        )


        binding.btnProjectApply.setOnClickListener {
            title = binding.CompanyName.text.toString()
            description = binding.ProjectDescription.text.toString()
            startDate = LocalDate.now().toString()
            jobGroup = binding.spinnerJobGroup.selectedItem.toString()
            job = binding.Job.text.toString()
            workStyle = binding.spinnerWorkStyle.selectedItem.toString()
            period = binding.Term.text.toString().toInt()
            pay = binding.Salary.text.toString().toInt()
            skill = binding.JobSkill.text.toString()
            wantCareer = binding.WantCareer.text.toString().toInt()

            val projectCreateRequest = ProjectCreateRequest(clientId, title, description, startDate, period, jobGroup, job, wantCareer, skill,  pay, workStyle)
            val projectService = retrofit.create(ProjectService::class.java)

            projectService.createProject(projectCreateRequest).enqueue(object: Callback<ProjectCreateResponse> {
                override fun onResponse(call: Call<ProjectCreateResponse>, response: Response<ProjectCreateResponse>
                ) {
                    Log.e("CreateProjectActivity", "result: ${response.body().toString()}")

                    val clientId = response.body()?.clientId
                    val projectId = response.body()?.projectId

                    val intent = Intent(this@ClientCreateProjectActivity, ClientCreateProjectCompleteActivity::class.java)
                    intent.putExtra("clientId", clientId)
                    intent.putExtra("projectId", projectId)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<ProjectCreateResponse>, t: Throwable) {
                    Log.e("CreateProjectActivity", t.toString())
                }
            })
        }
    }
}