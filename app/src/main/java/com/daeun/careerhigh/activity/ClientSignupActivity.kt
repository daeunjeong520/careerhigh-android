package com.daeun.careerhigh.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.api.ClientService
import com.daeun.careerhigh.databinding.ActivityClientSignupBinding
import com.daeun.careerhigh.vo.request.ClientSignupRequest
import com.daeun.careerhigh.vo.response.ClientSignupResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientSignupActivity: AppCompatActivity() {

    private lateinit var binding: ActivityClientSignupBinding

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var email:String = ""
        var password:String = ""
        var name:String = ""
        var phoneNumber:String = ""
        var companyName:String = ""
        var managerName = ""
        var managerPhone = ""

        binding.btnComplete.setOnClickListener {
            email = binding.EmailAddress.text.toString()
            password = binding.Password.text.toString()
            name = binding.Name.text.toString()
            phoneNumber = binding.PhoneNumber.text.toString()
            companyName = intent.getStringExtra("companyName").toString()
            managerName = intent.getStringExtra("managerName").toString()
            managerPhone = intent.getStringExtra("managerPhone").toString()

            val signupRequest = ClientSignupRequest(email, password, name, phoneNumber, companyName, managerName, managerPhone)
            val clientService = retrofit.create(ClientService::class.java)

            clientService.signup(signupRequest).enqueue(object: Callback<ClientSignupResponse> {
                override fun onResponse(
                    call: Call<ClientSignupResponse>,
                    response: Response<ClientSignupResponse>
                ) {
                    Log.e("SignupActivity", "user: ${response.body().toString()}")

                    // 회원가입 완료 페이지로 이동
                    val clientId = response.body()?.clientId
                    val name = response.body()?.name
                    val companyName = response.body()?.companyName
                    val managerName = response.body()?.managerName
                    val managerPhone = response.body()?.managerPhone

                    val intent = Intent(this@ClientSignupActivity, ClientSignupCompleteActivity::class.java)
                    intent.putExtra("clientId", clientId)
                    intent.putExtra("name", name)
                    intent.putExtra("companyName", companyName)
                    intent.putExtra("managerName", managerName)
                    intent.putExtra("managerPhone", managerPhone)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<ClientSignupResponse>, t: Throwable) {
                    Log.e("SignupActivity", t.toString())
                }
            })
        }
    }
}