package com.jinyong68.network.dto

import com.google.gson.annotations.SerializedName

data class GetMeDto(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("studentId")
    val studentId: String,
    @SerializedName("realName")
    val realName: String
)
