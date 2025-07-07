package com.jinyong68.network.dto

import com.google.gson.annotations.SerializedName

data class LoginRequestDto (
    @SerializedName("id")
    val id : String,
    @SerializedName("password")
    val password : String
)