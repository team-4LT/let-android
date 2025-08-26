package com.jinyong68.network.dto

import com.google.gson.annotations.SerializedName

data class SignupRequestDto(
    @SerializedName("username")
    val username : String,
    @SerializedName("studentId")
    val studentId : Int,
    @SerializedName("realName")
    val realName : String,
    @SerializedName("password")
    val password: String,
    @SerializedName("allergies")
    val allergies: List<String>
)
