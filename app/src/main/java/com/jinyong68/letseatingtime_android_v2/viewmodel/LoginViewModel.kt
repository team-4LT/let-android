package com.jinyong68.letseatingtime_android_v2.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jinyong68.data.TokenManager
import com.jinyong68.network.account.AccountRepository
import com.jinyong68.network.dto.LoginRequestDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _id = mutableStateOf("")
    private val _password = mutableStateOf("")

    val isLoading = mutableStateOf(false)
    val isError = mutableStateOf(false)

    fun setId(inputText: String) {
        _id.value = inputText
    }

    fun setPassword(inputText: String) {
        _password.value = inputText
    }

    fun login(onSuccess: () -> Unit) = viewModelScope.launch {
        val username = _id.value.trim()
        val password = _password.value.trim()

        if (username.isEmpty() || password.isEmpty()) return@launch

        isLoading.value = true
        isError.value = false

        try {
            val response = accountRepository.login(
                LoginRequestDto(username, password)
            )

            Log.d("Login", "서버 응답: $response")
            if (response.status == 200 || response.status == 201) {
                tokenManager.saveAccessToken(response.data.accessToken)
                tokenManager.saveRefreshToken(response.data.refreshToken)
                tokenManager.saveAutoLoginStatus(true)
                withContext(Dispatchers.Main) {
                    onSuccess()
                }
            } else {
                isError.value = true
            }

        } catch (e: retrofit2.HttpException) {
            Log.e("Login", "로그인 실패 HTTP ${e.code()}", e)
            isError.value = true
        } catch (e: Exception) {
            Log.e("Login", "로그인 실패", e)
            isError.value = true
        } finally {
            isLoading.value = false
        }
    }
}