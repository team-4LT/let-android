package com.jinyong68.network.account

import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.LoginRequestDto
import com.jinyong68.network.dto.ResponseDto
import com.jinyong68.network.dto.SignupRequestDto
import com.jinyong68.network.dto.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AccountApi {
    @Headers("Auth: false")
    @POST("auth/signup")
    suspend fun signup(@Body signupRequest: SignupRequestDto): BaseResponseDto

    @POST("/auth/login")
    suspend fun login(
        @Body request: LoginRequestDto
    ): ResponseDto<Token>


    @Headers("Auth: false")
    @POST("auth/refresh")
    suspend fun refresh(@Header("Authorization") refreshToken: String): Response<ResponseDto<Token>>
}