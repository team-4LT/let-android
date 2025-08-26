package com.jinyong68.network.user

import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.GetMeDto
import com.jinyong68.network.dto.MealAmountDto
import com.jinyong68.network.dto.MealRating
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {
    @GET("/users/me")
    suspend fun getMe(
        @Header("Authorization") token: String
    ): BaseResponseDto<GetMeDto>
    @POST("/meal-rating")
    suspend fun usersRating(@Body mealRating : MealRating) : BaseResponseDto<Nothing>

    @POST("/meal-amount")
    suspend fun mealAmounting(@Body mealAmountDto: MealAmountDto) : BaseResponseDto<Nothing>

    @PATCH("/menu/like/{menuId}")
    suspend fun voteMost(@Path("menuId") menuId : Int) : BaseResponseDto<Nothing>

    @PATCH("/menu/dislike/{menuId}")
    suspend fun voteMine(@Path("menuId") menuId : Int) : BaseResponseDto<Nothing>

    @PATCH("/eater")
    suspend fun usersCheck() : BaseResponseDto<Nothing>

}