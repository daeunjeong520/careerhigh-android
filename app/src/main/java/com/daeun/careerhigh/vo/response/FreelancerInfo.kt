package com.daeun.careerhigh.vo.response

import com.google.gson.annotations.SerializedName

data class FreelancerInfo(

    @SerializedName("freelancerId")
    val freelancerId: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("jobGroup")
    val jobGroup: String,

    @SerializedName("job")
    val job:String,

    @SerializedName("careerYear")
    val careerYear: Int,

    @SerializedName("work_style")
    val workStyle: String,

    @SerializedName("starRating")
    val starRating: Double,

    @SerializedName("profileImg")
    val profileImg: String
)