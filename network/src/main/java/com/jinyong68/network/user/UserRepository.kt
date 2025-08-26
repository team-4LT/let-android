package com.jinyong68.network.user

import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.GetMeDto
import com.jinyong68.network.dto.Meal
import com.jinyong68.network.dto.ResponseDto

interface UserRepository {
    suspend fun getMe(token: String): BaseResponseDto<GetMeDto>
    suspend fun usersRating(rating : Float) : BaseResponseDto<Nothing>
    suspend fun voteMost(mostMeal : List<String>) : BaseResponseDto<Nothing>
    suspend fun voteMine(mineMeal: List<String>) : BaseResponseDto<Nothing>
    suspend fun usersCheck(data : String) : BaseResponseDto<Nothing>
    suspend fun getMealAll() : ResponseDto<List<Meal>>
}