package com.daeun.careerhigh.vo.response

import com.google.gson.annotations.SerializedName

data class ProjectCreateResponse (

    @SerializedName("clientId")
    val clientId: Long,

    @SerializedName("projectId")
    val projectId: Long
)
