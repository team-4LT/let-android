package com.jinyong68.network.dto

data class WorkoutResponseDto(
    val id: Int,
    val title: String,
    val description: String,
    val method: String,
    val calorie: Int,
    val duration: Int,
    val category: String
)