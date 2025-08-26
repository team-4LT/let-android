package com.jinyong68.letseatingtime_android_v2.viewmodel

import android.util.Log
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jinyong68.network.account.AccountRepository
import com.jinyong68.network.dto.SignupRequestDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    val accountRepository: AccountRepository
) : ViewModel() {
    private val _id = mutableStateOf("")
    private val _name = mutableStateOf("")
    private val _number = mutableStateOf("")
    private val _password = mutableStateOf("")
    private val _checkPassword = mutableStateOf("")
    private val _isError = mutableStateOf(false)
    private val _errorMessage = mutableStateOf("")


    val id: MutableState<String> get() = _id
    val name: MutableState<String> get() = _name
    val number: MutableState<String> get() = _number
    val password: MutableState<String> get() = _password
    val checkPassword: MutableState<String> get() = _checkPassword
    val isError: MutableState<Boolean> get() = _isError
    val errorMessage: MutableState<String> get() = _errorMessage


    val allergyList = mutableStateOf(setOf<String>())

    fun changeAllergy(inputText: String) {
        allergyList.value = allergyList.value.toMutableSet().apply {
            if (!add(inputText)) remove(inputText)
        }
    }

    fun clearAllergy() {
        allergyList.value = emptySet()
    }

    fun validateSignUpId(): Boolean {
        val idVal = _id.value
        val pwVal = _password.value
        val rePwVal = _checkPassword.value
        return when {
            idVal.isBlank() -> {
                _isError.value = true
                _errorMessage.value = "아이디를 입력해주세요.."
                false
            }

            else -> {
                try {
                    if (pwVal.isBlank() || rePwVal.isBlank()) {
                        throw Exception("비밀번호를 입력해주세요.")
                    }
                    if (pwVal != rePwVal) {
                        throw Exception("비밀번호가 일치하지 않습니다.")
                    }
                    _isError.value = false
                    _errorMessage.value = ""
                    true
                } catch (e: Exception) {
                    _isError.value = true
                    _errorMessage.value = e.message ?: "입력 형식이 올바르지 않습니다."
                    false
                }
            }
        }
    }

    fun validateSignUpInfo(): Boolean {
        val nameVal = _name.value
        val numberVal = _number.value

        return when {
            nameVal.isBlank() -> {
                _isError.value = true
                _errorMessage.value = "이름을 입력해주세요."
                false
            }

            numberVal.length != 4 -> {
                _isError.value = true
                _errorMessage.value = "학번은 4자리여야 합니다."
                false
            }

            else -> {
                try {
                    val grade = numberVal[0].digitToInt()
                    val classNum = numberVal[1].digitToInt()
                    val num = numberVal.substring(2, 4).toInt()

                    when {
                        grade !in 1..3 -> throw Exception("학년은 1~3이어야 합니다.")
                        classNum !in 1..4 -> throw Exception("반은 1~4이어야 합니다.")
                        num !in 1..19 -> throw Exception("번호는 01~19 사이여야 합니다.")
                    }


                    _isError.value = false
                    _errorMessage.value = ""
                    true
                } catch (e: Exception) {
                    _isError.value = true
                    _errorMessage.value = e.message ?: "입력 형식이 올바르지 않습니다."
                    false
                }
            }
        }
    }


    private val _isSuccess = mutableStateOf(false)
    val isSuccess: MutableState<Boolean> get() = _isSuccess

    fun signUp() {
        viewModelScope.launch {
            println(id.value)
            println(name.value)
            println(password.value)
            println(number.value)
            try {
                if (id.value.isNotBlank() &&
                    name.value.isNotBlank() &&
                    password.value.isNotBlank() &&
                    number.value.isNotBlank()
                ) {
                    val signupRequest = SignupRequestDto(
                        username = _id.value,
                        studentId = _number.value.toInt(),
                        realName = _name.value,
                        password = _password.value,
                        allergies = allergyList.value.toList()
                    )

                    val response = accountRepository.signup(signupRequest)

                    _isError.value = false
                    _errorMessage.value = ""
                    Log.d("SignUp", "회원가입 성공: $response")

                    _isSuccess.value = true

                } else {
                    _isError.value = true
                    _errorMessage.value = "모든 항목을 입력해주세요."
                }
            } catch (e: retrofit2.HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                Log.e("SignUp", "HttpException: $errorBody")

                _isError.value = true
                _errorMessage.value = when (e.code()) {
                    400 -> errorBody ?: "잘못된 요청입니다."
                    409 -> "이미 존재하는 아이디입니다."
                    else -> "회원가입 실패 (code: ${e.code()})"
                }
            } catch (e: Exception) {
                Log.e("SignUp", "Exception", e)
                _isError.value = true
                _errorMessage.value = e.message ?: "회원가입 중 알 수 없는 오류가 발생했습니다."
            }
        }
    }

    fun getValue(): Array<MutableState<String>> {
        return arrayOf(_id, _name, _password, _checkPassword, _number)
    }

    fun logValues() {
        val values = getValue()
        values.forEachIndexed { index, state ->
            Log.d("FormState", "Field $index: ${state.value}")
        }
    }
}