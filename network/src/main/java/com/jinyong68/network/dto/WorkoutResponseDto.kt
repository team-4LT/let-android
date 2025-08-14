package com.jinyong68.network.dto

data class WorkoutResponseDto(
    val id: Int,
    val title: String,
    val description: String,
    val explain: String,
    val calorie: Int,
    val time: Int,
    val type: String
)