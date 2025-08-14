package com.jinyong68.network.account

import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.LoginRequestDto
import com.jinyong68.network.dto.ResponseDto
import com.jinyong68.network.dto.SignupRequestDto
import com.jinyong68.network.dto.Token
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AccountRepository {
    suspend fun signup(signupRequest: SignupRequestDto): BaseResponseDto
    suspend fun login(loginRequest: LoginRequestDto): ResponseDto<Token>
    suspend fun refresh(refreshToken: String): Response<ResponseDto<Token>>
}