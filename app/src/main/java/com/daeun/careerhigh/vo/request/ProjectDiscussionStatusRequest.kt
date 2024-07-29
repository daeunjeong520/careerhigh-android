package com.daeun.careerhigh.vo.request

import com.google.gson.annotations.SerializedName

data class ProjectDiscussionStatusRequest(

    @SerializedName("projectId")
    val projectId: Long,
    @SerializedName("freelancerId")
    val freelancerId: Long,
    @SerializedName("status")
    val status: String
)
