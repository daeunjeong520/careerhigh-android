package com.daeun.careerhigh.vo.request

import com.google.gson.annotations.SerializedName

data class ProjectDiscussionRequest(
    @SerializedName("freelancerId")
    val freelancerId: Long,

    @SerializedName("projectId")
    val projectId: Long
)
