package com.daeun.careerhigh.vo.response

import com.google.gson.annotations.SerializedName

data class ProjectRequestResponse(

    @SerializedName("clientId")
    val clientId: Long,

    @SerializedName("freelancerId")
    val freelancerId: Long,

    @SerializedName("projectId")
    val projectId: Long
)
