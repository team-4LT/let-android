package com.jinyong68.network.workout

import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.WorkoutResponseDto
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor(
    private val workoutApi: WorkoutApi
) : WorkoutRepository {
    override suspend fun workoutList(): BaseResponseDto<List<WorkoutResponseDto>> {
        return workoutApi.workoutList()
    }
    override suspend fun workoutRecommend(): BaseResponseDto<List<WorkoutResponseDto>> {
        return workoutApi.workoutRecommend()
    }
}