package com.jinyong68.letseatingtime_android_v2.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jinyong68.data.TokenManager
import com.jinyong68.di.Constant.AI_BASE_URL
import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.Exercise
import com.jinyong68.network.dto.LoginRequestDto
import com.jinyong68.network.dto.MealResponseDto
import com.jinyong68.network.dto.RecommendResponse
import com.jinyong68.network.dto.WorkoutResponseDto
import com.jinyong68.network.meal.MealRepository
import com.jinyong68.network.user.UserRepository
import com.jinyong68.network.workout.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mealRepository: MealRepository,
    private val userRepository: UserRepository,
    private val tokenManager: TokenManager
) : ViewModel() {
    init {
        fetchMenu(LocalDate.now().toString())
        fetchWorkoutRecommend()
    }
    val _date = LocalDate.now()
    val isLoading = mutableStateOf(false)
    val isError = mutableStateOf(false)
    val isAttend = mutableStateOf(false)
    val modalVisibility = mutableStateOf(false)
    val mealList = mutableStateOf<List<MealResponseDto>>(emptyList())
    val workoutRecommend = mutableStateOf<List<Exercise>>(emptyList())
    val message = mutableStateOf("오늘은 00kcal섭취 하셨네요! 이 운동을 통해 00만큼 운동해 보아요!")
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

    fun fetchMenu(today: String) = viewModelScope.launch {
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


    val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json() // kotlinx.serialization 사용
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 60_000   // 전체 요청 최대 60초
            connectTimeoutMillis = 30_000   // 서버 연결 시도 최대 30초
            socketTimeoutMillis = 30_000    // 서버 응답 대기 시간 30초
        }
    }

    suspend fun fetchWorkoutRecommendApi(): RecommendResponse{
        val token = tokenManager.getAccessToken().first()
        return client.post("${AI_BASE_URL}/api/exercises/recommend") {
            // Bearer 토큰을 Authorization 헤더에 추가
            headers {
                append("Authorization", "Bearer $token")
            }
            contentType(ContentType.Application.Json)
            setBody("") // curl -d '' 처럼 빈 바디
        }.body()
    }

    fun fetchWorkoutRecommend() = viewModelScope.launch {
        Log.d("시작","끝")
        val token = tokenManager.getAccessToken()
        Log.d("토큰","$token")
        try {
            val response = fetchWorkoutRecommendApi()
            Log.d("Workout", "서버 응답: $response")

            if (response.success == true) {
                message.value = response.recommendation.message
                workoutRecommend.value = response.recommendation.recommended_exercises
            } else {
                isError.value = true
            }
        }catch (e: HttpException) {
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

    fun attendsRequest()  = viewModelScope.launch{
        try {
            val response = userRepository.usersCheck()
            Log.d("Attends", "출석 응답 $response")
        } catch (e : HttpException) {
            Log.d("Attends", "출석 실패 ${e.code()}")
        }
    }
}

