package com.daeun.careerhigh.vo.response

import com.google.gson.annotations.SerializedName

data class ProjectRequestResponse(

    @SerializedName("freelancerProjectId")
    val freelancerProjectId: Long,

    @SerializedName("status")
    val status: String
)
