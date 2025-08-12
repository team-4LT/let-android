package com.jinyong68.network.dto

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("accessToken")
    val accessToken : String,
    @SerializedName("refreshToken")
    val refreshToken : String
)
