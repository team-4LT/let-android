package com.jinyong68.network.meal

import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.MealResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MealApi {
    @GET("mealMenu/daily/{date}")
    suspend fun menu(
        @Path("date") date: String
    ): BaseResponseDto<List<MealResponseDto>>
}
