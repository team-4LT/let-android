package com.jinyong68.letseatingtime_android_v2.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jinyong68.network.dto.LoginRequestDto
import com.jinyong68.network.dto.MealResponseDto
import com.jinyong68.network.dto.WorkoutResponseDto
import com.jinyong68.network.meal.MealRepository
import com.jinyong68.network.user.UserRepository
import com.jinyong68.network.workout.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mealRepository: MealRepository,
    private val workoutRepository: WorkoutRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    val _date = LocalDate.now()
    val isLoading = mutableStateOf(false)
    val isError = mutableStateOf(false)
    val isAttend = mutableStateOf(false)
    val mealList = mutableStateOf<List<MealResponseDto>>(emptyList())
    val workoutRecommend = mutableStateOf<List<WorkoutResponseDto>>(emptyList())
    val year = _date.year
    val month = _date.monthValue
    val firstDayOfMonth = LocalDate.now().withDayOfMonth(1)
    var clickedDay = mutableStateOf(LocalDate.now().dayOfMonth)
    val dayOfWeek = when (_date.dayOfWeek) { // 요일
        java.time.DayOfWeek.MONDAY -> "월"
        java.time.DayOfWeek.TUESDAY -> "화"
        java.time.DayOfWeek.WEDNESDAY -> "수"
        java.time.DayOfWeek.THURSDAY -> "목"
        java.time.DayOfWeek.FRIDAY -> "금"
        java.time.DayOfWeek.SATURDAY -> "토"
        java.time.DayOfWeek.SUNDAY -> "일"
    }

    fun getMaxDayOfMonth(year: Int, month: Int): Int {
        return if (month == 2) {
            if (isLeapYear(year)) 29 else 28
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            30
        } else {
            31
        }
    }

    fun isLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }

    fun fetchMenu(today: String = _date.toString()) = viewModelScope.launch {
        try{
            val response = mealRepository.menu(date = today.toString())
            Log.d("Meal", "서버 응답: $response")

            if (response.status == 200 || response.status == 201) {
                mealList.value = response.data ?: emptyList()
            } else {
                isError.value = true
            }

        } catch (e: retrofit2.HttpException) {
            Log.e("Meal", "급식 불러오기 실패 ${e.code()}", e)
            isError.value = true
        } catch (e: Exception) {
            Log.e("Meal", "급식 불러오기 실패", e)
            isError.value = true
        } finally {
            isLoading.value = false
            }
    }

    fun fetchWorkoutRecommend() = viewModelScope.launch {
        try{
            val response = workoutRepository.workoutRecommend()
            Log.d("Workout", "서버 응답: $response")

            if (response.status == 200 || response.status == 201) {
                workoutRecommend.value = response.data ?: emptyList()
            } else {
                isError.value = true
            }
        }
        catch (e: retrofit2.HttpException) {
            Log.e("Workout", "운동 불러오기 실패 ${e.code()}", e)
            isError.value = true
        }
        catch (e: Exception) {
            Log.e("Workout", "운동 불러오기 실패", e)
            isError.value = true
        } finally {
            isLoading.value = false
        }
    }

    fun attendsRequest(data : String)  = viewModelScope.launch{
        try {
            val response = userRepository.usersCheck(data)
            Log.d("Attends", "출석 응답 $response")
        } catch (e : HttpException) {
            Log.d("Attends", "출석 실패 ${e.code()}")
        }
    }
}

