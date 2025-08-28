package com.jinyong68.network.workout

import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.WorkoutResponseDto

interface WorkoutRepository {
    suspend fun workoutList(): BaseResponseDto<List<WorkoutResponseDto>>
}