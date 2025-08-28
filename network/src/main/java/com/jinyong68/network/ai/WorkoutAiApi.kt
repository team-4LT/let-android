package com.jinyong68.network.ai

import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.WorkoutResponseDto
import retrofit2.http.GET

interface WorkoutAiApi {
    @GET("http://43.203.235.45:8001/api/exercises/recommend")
    suspend fun workoutRecommend(
    ): BaseResponseDto<List<WorkoutResponseDto>>
}