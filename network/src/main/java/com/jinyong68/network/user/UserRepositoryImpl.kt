package com.jinyong68.network.user

import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.GetMeDto
import com.jinyong68.network.dto.Meal
import com.jinyong68.network.dto.MealAmountDto
import com.jinyong68.network.dto.MealRating
import com.jinyong68.network.dto.ResponseDto
import com.jinyong68.network.dto.Token
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun usersRating(mealRating: MealRating): BaseResponseDto<Nothing> {
        return userApi.usersRating(mealRating)
    }

    override suspend fun voteMost(menuId: Int): BaseResponseDto<Nothing> {
        return userApi.voteMost(menuId)
    }

    override suspend fun voteMine(menuId : Int): BaseResponseDto<Nothing> {
        return userApi.voteMine(menuId)
    }

    override suspend fun usersCheck(): BaseResponseDto<Nothing> {
        return userApi.usersCheck()
    }

    override suspend fun mealAmounting(mealAmountDto: MealAmountDto): BaseResponseDto<Nothing> {
        return userApi.mealAmounting(mealAmountDto)
    }

    override suspend fun getMe(token: String): BaseResponseDto<GetMeDto> {
        return userApi.getMe("Bearer $token")
    }
}