package com.jinyong68.network.dto

import com.google.gson.annotations.SerializedName

data class BaseResponseDto<T>(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: T?
)
