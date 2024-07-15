package com.daeun.careerhigh.vo.request

import com.google.gson.annotations.SerializedName

data class ClientLoginRequest(

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)