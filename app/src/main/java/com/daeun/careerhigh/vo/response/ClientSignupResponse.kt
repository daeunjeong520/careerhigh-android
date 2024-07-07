package com.daeun.careerhigh.vo.response

import com.google.gson.annotations.SerializedName

data class ClientSignupResponse(
    @SerializedName("clientId")
    val clientId: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("companyName")
    val companyName: String,

    @SerializedName("managerName")
    val managerName: String,

    @SerializedName("managerPhone")
    val managerPhone: String
)