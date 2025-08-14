package com.jinyong68.network.meal

import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.MealResponseDto
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealApi: MealApi
) : MealRepository {
    override suspend fun menu(date: String): BaseResponseDto<List<MealResponseDto>> {
        return mealApi.menu(date)
    }
}
