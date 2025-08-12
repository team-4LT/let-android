package com.jinyong68.letseatingtime_android_v2.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jinyong68.network.account.AccountApi
import com.jinyong68.network.account.AccountRepository
import com.jinyong68.network.dto.LoginRequestDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel(){
    private val _id = mutableStateOf("")
    private val _password = mutableStateOf("")

    fun setId(inputText : String){
        _id.value = inputText
    }

    fun setPassword(inputText: String){
        _password.value = inputText
    }

    fun login() = viewModelScope.launch {
        runCatching {
            accountRepository.login(
                LoginRequestDto(_id.value, _password.value)
            ).collect { response ->
                Log.d("Login", "서버 응답: $response")
            }
        }.onFailure { throwable ->
            Log.e("Login", "로그인 실패", throwable)
        }
    }
}