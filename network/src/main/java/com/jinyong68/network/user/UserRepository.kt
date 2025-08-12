package com.jinyong68.network.user

import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.Meal
import com.jinyong68.network.dto.ResponseDto
import com.jinyong68.network.dto.Workout

interface UserRepository {
    suspend fun usersRating(rating : Float) : BaseResponseDto
    suspend fun voteMost(mostMeal : List<String>) : BaseResponseDto
    suspend fun voteMine(mineMeal: List<String>) : BaseResponseDto
    suspend fun usersCheck(data : String) : BaseResponseDto
    suspend fun usersWorkout(mealState : Boolean) : ResponseDto<Workout>
    suspend fun getMealAll() : ResponseDto<List<Meal>>
    suspend fun getWorkoutAll() : ResponseDto<List<Workout>>
}