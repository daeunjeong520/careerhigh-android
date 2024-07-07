package com.daeun.careerhigh.api

import com.daeun.careerhigh.vo.request.ClientSignupRequest
import com.daeun.careerhigh.vo.response.ClientSignupResponse
import com.daeun.careerhigh.vo.response.FreelancerInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ClientService {

    @Headers("Content-Type: application/json")
    @POST("api/clients/signup")
    fun signup(@Body clientSignupRequest: ClientSignupRequest): Call<ClientSignupResponse>


}