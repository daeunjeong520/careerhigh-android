package com.daeun.careerhigh.vo.response

import com.google.gson.annotations.SerializedName

data class ClientLoginResponse(

    @SerializedName("clientId")
    val clientId: Long,

    @SerializedName("name")
    val name: String
)
