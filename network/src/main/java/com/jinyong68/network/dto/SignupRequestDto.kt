package com.jinyong68.network.dto

import com.google.gson.annotations.SerializedName

data class SignupRequestDto(
    @SerializedName("name")
    val name : String,
    @SerializedName("studentId")
    val studentId : String,
    @SerializedName("schoolId")
    val schoolId : String,
    @SerializedName("id")
    val id : String,
    @SerializedName("password")
    val password: String,
    @SerializedName("rePassword")
    val rePassword : String
)
