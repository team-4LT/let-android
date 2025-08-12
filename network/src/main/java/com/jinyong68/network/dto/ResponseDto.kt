package com.jinyong68.network.dto

import com.google.gson.annotations.SerializedName

data class ResponseDto<T>(
    @SerializedName("status")
    val status : Int,
    @SerializedName("massage")
    val massage : String,
    @SerializedName("data")
    val data : T
)
