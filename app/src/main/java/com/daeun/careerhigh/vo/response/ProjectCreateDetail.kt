package com.daeun.careerhigh.vo.response

import com.google.gson.annotations.SerializedName

data class ProjectCreateDetail(

    @SerializedName("clientId")
    val clientId: Long,
    
    @SerializedName("companyName")
    val companyName: String,

    @SerializedName("projectId")
    val projectId: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("startDate")
    val startDate: String,

    @SerializedName("period")
    val period: Int,

    @SerializedName("jobGroup")
    val jobGroup: String,

    @SerializedName("job")
    val job: String,

    @SerializedName("skill")
    val skill: String,

    @SerializedName("pay")
    val pay: Int,

    @SerializedName("wantCareerYear")
    val wantCareerYear: String,

    @SerializedName("workStyle")
    val workStyle: String
)
