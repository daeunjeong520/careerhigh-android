package com.daeun.careerhigh.vo.request

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class ProjectCreateRequest (

    @SerializedName("clientId")
    val clientId: Long,

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

    @SerializedName("wantCareerYear")
    val wantCareerYear: Int,

    @SerializedName("skill")
    val skill: String,

    @SerializedName("pay")
    val pay: Int,

    @SerializedName("workStyle")
    val workStyle: String
)


