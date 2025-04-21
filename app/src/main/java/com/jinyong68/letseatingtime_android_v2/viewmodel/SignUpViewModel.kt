package com.jinyong68.letseatingtime_android_v2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

) : ViewModel() {
    private val _id = mutableStateOf("")
    private val _name = mutableStateOf("")
    private val _studentId = mutableStateOf("")
    private val _schoolId = mutableStateOf("")
    private val _password = mutableStateOf("")
    private val _checkPassword = mutableStateOf("")

    fun setId(inputText: String) {
        _id.value = inputText
    }

    fun setName(inputText: String) {
        _name.value = inputText
    }

    fun setStudent(inputText: String) {
        _studentId.value = inputText
    }

    fun setSchoolId(inputText : String) {
        _schoolId.value = inputText
    }

    fun setPassword(inputText: String) {
        _password.value = inputText
    }

    fun setCheckPassword(inputText : String) {
        _checkPassword.value = inputText
    }

    fun signUp() = viewModelScope.launch {
        runCatching {
            TODO()
        }
    }
}