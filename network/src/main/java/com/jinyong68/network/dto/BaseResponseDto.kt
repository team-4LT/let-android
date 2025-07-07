package com.jinyong68.network.dto

import com.google.gson.annotations.SerializedName

data class BaseResponseDto(
    @SerializedName("status")
    val status : Int,
    @SerializedName("massage")
    val massage : String,
)
