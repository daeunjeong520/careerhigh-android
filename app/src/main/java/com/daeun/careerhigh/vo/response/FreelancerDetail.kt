package com.daeun.careerhigh.vo.response

import com.google.gson.annotations.SerializedName

data class FreelancerDetail(

    @SerializedName("freelancerId")
    val freelancerId: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("jobGroup")
    val jobGroup: String,

    @SerializedName("job")
    val job: String,

    @SerializedName("workStyle")
    val workStyle: String,

    @SerializedName("careerYear")
    val careerYear: Int,

    @SerializedName("starRating")
    val starRating: Double,

    @SerializedName("profileImg")
    val profileImg: String,

    @SerializedName("skill")
    val skill: String,

    @SerializedName("link")
    val link: String,

    @SerializedName("proficiency")
    val proficiency: String,

    @SerializedName("description")
    val description: String
)