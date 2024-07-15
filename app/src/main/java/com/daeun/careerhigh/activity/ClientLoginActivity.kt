package com.daeun.careerhigh.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daeun.careerhigh.api.ClientService
import com.daeun.careerhigh.databinding.ActivityClientLoginBinding
import com.daeun.careerhigh.vo.request.ClientLoginRequest
import com.daeun.careerhigh.vo.response.ClientLoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientLoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityClientLoginBinding
    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 로그인 -> 메인 페이지
        binding.loginButton.setOnClickListener {
            val id = binding.idInput.text.toString()
            val password = binding.passwordInput.text.toString()

            val request = ClientLoginRequest(id, password)
            login(request)
        }

        // 로그인 화면 -> 프로필 생성 페이지
        binding.signUpButton.setOnClickListener {
            val intent = Intent(this@ClientLoginActivity, ClientProfileCreateActivity::class.java)
            startActivity(intent)
        }
    }

    // 로그인
    private fun login(request: ClientLoginRequest) {
        val clientService = retrofit.create(ClientService::class.java)

        clientService.login(request).enqueue(object: Callback<ClientLoginResponse> {
            override fun onResponse(call: Call<ClientLoginResponse>, response: Response<ClientLoginResponse>) {
                Log.e("ClientLoginActivity", "Login Result: ${response.body().toString()}")

                val clientId = response.body()?.clientId
                val name = response.body()?.name

                val intent = Intent(this@ClientLoginActivity, ClientMainActivity::class.java)
                intent.putExtra("clientId", clientId)
                intent.putExtra("name", name)
                startActivity(intent)
            }

            override fun onFailure(call: Call<ClientLoginResponse>, t: Throwable) {
                Toast.makeText(this@ClientLoginActivity, "아이디 또는 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
                Log.e("ClientLoginActivity", t.toString())
            }
        })
    }
}