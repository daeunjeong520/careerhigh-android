package com.daeun.careerhigh.vo.response

import com.google.gson.annotations.SerializedName

data class ProjectDiscussionStatusResponse(

    @SerializedName("projectId")
    val projectId: Long,
    @SerializedName("freelancerId")
    val freelancerId: Long,
    @SerializedName("clientId")
    val clientId: Long,
    @SerializedName("clientStatus")
    val clientStatus: String,
    @SerializedName("freelancerStatus")
    val freelancerStatus: String

)
