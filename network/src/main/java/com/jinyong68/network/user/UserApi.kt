package com.jinyong68.network.user

import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.Meal
import com.jinyong68.network.dto.ResponseDto
import com.jinyong68.network.dto.Workout
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @POST("/users/rating")
    suspend fun usersRating(@Body rating : Float) : BaseResponseDto<Nothing>

    @POST("vote/most")
    suspend fun voteMost(@Body mostMeal : List<String>) : BaseResponseDto<Nothing>

    @POST("vote/mine")
    suspend fun voteMine(@Body mineMeal: List<String>) : BaseResponseDto<Nothing>

    @POST("users/check")
    suspend fun usersCheck(@Body data : String) : BaseResponseDto<Nothing>

    @GET("users/workout")
    suspend fun usersWorkout(@Body mealState : Boolean) : ResponseDto<Workout>

    @GET("/meal")
    suspend fun getMealAll() : ResponseDto<List<Meal>>

    @GET("/workout")
    suspend fun getWorkout() : ResponseDto<List<Workout>>
}