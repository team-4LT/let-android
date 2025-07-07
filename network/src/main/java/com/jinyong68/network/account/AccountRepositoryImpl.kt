package com.jinyong68.network.account

import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.LoginRequestDto
import com.jinyong68.network.dto.ResponseDto
import com.jinyong68.network.dto.SignupRequestDto
import com.jinyong68.network.dto.Token
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountApi: AccountApi
) : AccountRepository {
    override suspend fun signup(signupRequest: SignupRequestDto): BaseResponseDto {
        return accountApi.signup(signupRequest)
    }

    override suspend fun login(loginRequest: LoginRequestDto) = flow {
        emit(accountApi.login(loginRequest))
    }

    override suspend fun refresh(refreshToken: String): Response<ResponseDto<Token>> {
        return accountApi.refresh(refreshToken)
    }
}