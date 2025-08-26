package com.jinyong68.letseatingtime_android_v2.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jinyong68.network.dto.MealAmountDto
import com.jinyong68.network.dto.MealRating
import com.jinyong68.network.dto.MealResponseDto
import com.jinyong68.network.meal.MealRepository
import com.jinyong68.network.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class QuestionnaireViewModel@Inject constructor(
    private val userRepository: UserRepository,
    private val mealRepository: MealRepository
) : ViewModel() {
    val _date = LocalDate.now()
    val isError = mutableStateOf(false)
    val isLoading = mutableStateOf(false)
    val rating : Int = 0
    val mealAmount = "FEW | SUITABLE | MUCH"
    val likeMealList = intArrayOf()
    val dislikeMealList = intArrayOf()

    var step = 1

    fun fetchMenu(today: String = _date.toString()) = viewModelScope.launch {
        try{
            val response = mealRepository.menu(date = today.toString())
            Log.d("Meal", "서버 응답: $response")

            if (response.status == 200 || response.status == 201) {
                todayMeal.value = response.data ?: emptyList()
                nowMeal = todayMeal.value[getMealType()]
            } else {
                isError.value = true
            }

        } catch (e: HttpException) {
            Log.e("Meal", "급식 불러오기 실패 ${e.code()}", e)
            isError.value = true
        } catch (e: Exception) {
            Log.e("Meal", "급식 불러오기 실패", e)
            isError.value = true
        } finally {
            isLoading.value = false
        }
    }

    val todayMeal = mutableStateOf<List<MealResponseDto>>(listOf(MealResponseDto(
        mealId = 99999999,
        mealDate = _date.toString(),
        mealType = "조식",
        calorie = 1000.0,
        menus = listOf()
    )))
    var nowMeal = todayMeal.value[getMealType()]

    fun getMealType() : Int {
        val now = LocalTime.now()

        val morningStart = LocalTime.of(7, 40)   // 07:40
        val morningEnd = LocalTime.of(8, 20)     // 08:20

        val lunchStart = LocalTime.of(12, 40)    // 12:40
        val lunchEnd = LocalTime.of(13, 30)      // 13:30

        val dinnerStart = LocalTime.of(18, 10)   // 18:10
        val dinnerEnd = LocalTime.of(19, 20)     // 19:20

        when {
            now.isAfter(morningStart) && now.isBefore(morningEnd) -> {
                return 0
            }

            now.isAfter(lunchStart) && now.isBefore(lunchEnd) -> {
                return 1
            }

            now.isAfter(dinnerStart) && now.isBefore(dinnerEnd) -> {
                return 2
            }
        }
        return 0;
    }

    fun sendUserRating(mealRating: MealRating) {
        viewModelScope.launch {
            runCatching {
                userRepository.usersRating(mealRating)
            }.onSuccess { response ->
                // 성공 처리
                println("✅ Rating 성공: $response")
            }.onFailure { error ->
                // 에러 처리
                println("❌ Rating 실패: ${error.message}")
            }
        }
    }

    fun sendMealAmount(mealAmountDto: MealAmountDto) {
        viewModelScope.launch {
            runCatching {
                userRepository.mealAmounting(mealAmountDto)
            }.onSuccess { response ->
                Log.d("Meal","Meal Amount 성공: $response")
            }.onFailure { error ->
                Log.d("Meal","❌ Meal Amount 실패: ${error.message}")
            }
        }
    }

    fun voteMostMenu(menuId: Int) {
        viewModelScope.launch {
            runCatching {
                userRepository.voteMost(menuId)
            }.onSuccess { response ->
                Log.d("Like","✅ voteMost 성공: $response")
            }.onFailure { error ->
                Log.d("Like","❌ voteMost 실패: ${error.message}")
            }
        }
    }

    fun voteMineMenu(menuId: Int) {
        viewModelScope.launch {
            runCatching {
                userRepository.voteMine(menuId)
            }.onSuccess { response ->
                Log.d("Dislike","✅ voteMine 성공: $response")
            }.onFailure { error ->
                Log.d("Dislike","❌ voteMine 실패: ${error.message}")
            }
        }
    }
}