@file:OptIn(kotlinx.serialization.InternalSerializationApi::class)
package com.jinyong68.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class RecommendResponse(
    val success: Boolean,
    val user_id: String,
    val daily_calories: Double,
    val data_source: String,
    val recommendation: Recommendation
)

@Serializable
data class Recommendation(
    val message: String,
    val recommended_exercises: List<Exercise>
)

@Serializable
data class Exercise(
    val id: Int,
    val title: String,
    val category: String,
    val recommended_duration: Int,
    val description: String,
    val method: String,
    val expected_calories: Int,
    val selection_reason: String
)
