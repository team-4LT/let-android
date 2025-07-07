package com.jinyong68.network.dto

import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("menu")
    val menu : List<String>
)
