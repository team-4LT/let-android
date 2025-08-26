package com.jinyong68.network.dto

import com.google.gson.annotations.SerializedName

data class MealAmountDto(
    @SerializedName("mealId")
    val mealId : Int,
    @SerializedName("rating")
    val rating : String,
)
