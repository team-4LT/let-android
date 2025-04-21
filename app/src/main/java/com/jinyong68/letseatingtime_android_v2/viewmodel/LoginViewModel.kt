package com.jinyong68.letseatingtime_android_v2.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

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
        runCatching { TODO() }
    }
}