package com.jinyong68.network.user

import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.GetMeDto
import com.jinyong68.network.dto.MealAmountDto
import com.jinyong68.network.dto.MealRating

interface UserRepository {
    suspend fun getMe(token: String): BaseResponseDto<GetMeDto>
    suspend fun usersRating(mealRating: MealRating) : BaseResponseDto<Nothing>
    suspend fun mealAmounting (mealAmountDto: MealAmountDto) : BaseResponseDto<Nothing>
    suspend fun voteMost(menuId : Int) : BaseResponseDto<Nothing>
    suspend fun voteMine(menuId : Int) : BaseResponseDto<Nothing>
    suspend fun usersCheck() : BaseResponseDto<Nothing>
}