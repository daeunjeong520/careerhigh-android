package com.daeun.careerhigh.vo.request

import com.google.gson.annotations.SerializedName

data class ClientSignupRequest(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("phoneNumber")
    val phoneNumber: String,

    @SerializedName("companyName")
    val companyName: String,

    @SerializedName("managerName")
    val managerName: String,

    @SerializedName("managerPhone")
    val managerPhone: String

)