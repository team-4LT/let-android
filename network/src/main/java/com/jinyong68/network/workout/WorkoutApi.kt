package com.jinyong68.network.workout

import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.WorkoutResponseDto
import retrofit2.http.GET

interface WorkoutApi {
    @GET("/exercises")
    suspend fun workoutList(
    ): BaseResponseDto<List<WorkoutResponseDto>>

    @GET("/workout/recommend")
    suspend fun workoutRecommend(
    ): BaseResponseDto<List<WorkoutResponseDto>>
}