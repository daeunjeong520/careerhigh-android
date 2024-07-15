package com.daeun.careerhigh.vo.response

import com.google.gson.annotations.SerializedName

data class ProjectInfo (

    @SerializedName("projectId")
    val projectId: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("jobGroup")
    val jobGroup: String,

    @SerializedName("job")
    val job: String,

    @SerializedName("workStyle")
    val workStyle: String,

    @SerializedName("pay")
    val pay: Int,

    @SerializedName("startDate")
    val startDate: String,

    @SerializedName("period")
    val period: Int
)