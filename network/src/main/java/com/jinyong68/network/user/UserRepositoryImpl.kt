package com.jinyong68.network.user

import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.Meal
import com.jinyong68.network.dto.ResponseDto
import com.jinyong68.network.dto.Workout
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun usersRating(rating: Float): BaseResponseDto<Nothing> {
        return userApi.usersRating(rating)
    }

    override suspend fun voteMost(mostMeal: List<String>): BaseResponseDto<Nothing> {
        return userApi.voteMost(mostMeal)
    }

    override suspend fun voteMine(mineMeal: List<String>): BaseResponseDto<Nothing> {
        return userApi.voteMine(mineMeal)
    }

    override suspend fun usersCheck(data: String): BaseResponseDto<Nothing> {
        return userApi.usersCheck(data)
    }

    override suspend fun usersWorkout(mealState: Boolean): ResponseDto<Workout> {
        return userApi.usersWorkout(mealState)
    }

    override suspend fun getMealAll(): ResponseDto<List<Meal>> {
        return userApi.getMealAll()
    }

    override suspend fun getWorkoutAll(): ResponseDto<List<Workout>> {
        return userApi.getWorkout()
    }

}