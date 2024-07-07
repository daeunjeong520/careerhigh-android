package com.daeun.careerhigh.api

import com.daeun.careerhigh.vo.response.FreelancerDetail
import com.daeun.careerhigh.vo.response.FreelancerInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FreelancerService {

    @GET("api/freelancers")
    fun getAllFreelancers(): Call<List<FreelancerInfo>>

    @GET("api/freelancers/search")
    fun searchFreelancersByJob(@Query("query") query: String): Call<List<FreelancerInfo>>

    @GET("api/freelancers/{freelancerId}")
    fun getFreelancer(@Path("freelancerId") freelancerId: Long): Call<FreelancerDetail>
}