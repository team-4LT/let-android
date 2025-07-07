package com.jinyong68.network.dto

import com.google.gson.annotations.SerializedName

data class Workout(
    @SerializedName("name")
    val name : String,
    @SerializedName("mode")
    val mode : String
)
