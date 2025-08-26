package com.jinyong68.letseatingtime_android_v2.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jinyong68.data.TokenManager
import com.jinyong68.network.dto.GetMeDto
import com.jinyong68.network.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    var me = mutableStateOf<GetMeDto?>(null)
        private set


    fun fetchMe(response: GetMeDto?) {
        me.value = response
    }

    fun getMe(){
        viewModelScope.launch {
            try{
                tokenManager.getAccessToken().collect {  token ->
                    if (token.isNotEmpty()){
                        val response = userRepository.getMe(token)
                        if (response.status == 200){
                            println("서버 성공")
                            response.data?.let { safeData ->
                                fetchMe(safeData)
                            }
                        }
                    }
                }
            }catch (error: Exception){
                Log.e("에러", "dpfj")
            }
        }
    }
}