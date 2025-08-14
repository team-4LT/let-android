package com.jinyong68.network.meal

import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.MealResponseDto

interface MealRepository {
    suspend fun menu( date: String): BaseResponseDto<List<MealResponseDto>>
}