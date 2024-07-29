package com.daeun.careerhigh.vo.response

import com.google.gson.annotations.SerializedName

data class ProjectDiscussionDetail(

    @SerializedName("clientId")
    val clientId: Long,
    @SerializedName("freelancerId")
    val freelancerId: Long,
    @SerializedName("projectId")
    val projectId: Long,
    @SerializedName("companyName")
    val companyName: String,
    @SerializedName("freelancerName")
    val freelancerName: String,
    @SerializedName("projectName")
    val projectName: String,
    @SerializedName("freelancerImgPath")
    val freelancerImgPath: String,

    @SerializedName("jobGroup")
    val jobGroup: String,
    @SerializedName("job")
    val job: String,
    @SerializedName("careerYear")
    val careerYear: Int,
    @SerializedName("starRating")
    val starRating: Double,

    @SerializedName("startDate")
    val startDate: String,

    @SerializedName("proficiency")
    val proficiency: String,

    @SerializedName("skill")
    val skill: String,

    @SerializedName("title")
    val title: String,
    @SerializedName("salary")
    val salary: Int,
    @SerializedName("workStyle")
    val workStyle: String,
    @SerializedName("description")
    val description: String,

    @SerializedName("freelancerStatus")
    val freelancerStatus: String
)
