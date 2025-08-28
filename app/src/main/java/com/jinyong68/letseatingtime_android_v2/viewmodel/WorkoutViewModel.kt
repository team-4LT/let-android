package com.jinyong68.letseatingtime_android_v2.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jinyong68.di.Constant.AI_BASE_URL
import com.jinyong68.network.dto.BaseResponseDto
import com.jinyong68.network.dto.WorkoutResponseDto
import com.jinyong68.network.workout.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository
) : ViewModel() {

    val selectedWorkout = mutableStateOf<WorkoutResponseDto?>(null)
    val isLoading = mutableStateOf(false)
    val isError = mutableStateOf(false)
    val workoutList = mutableStateOf<List<WorkoutResponseDto>>(emptyList())
    // 현재 운동을 얼마나 했는지
    val workoutTime = mutableStateOf(0)
    val isRunning = mutableStateOf(false)
    var isModalClicked by mutableStateOf(false)
        private set
    var level by mutableStateOf("초급")
        private set

    private var timerJob: Job? = null

    fun startTimer() {
        if (timerJob?.isActive == true) return
        isRunning.value = true
        timerJob = viewModelScope.launch {
            while (workoutTime.value > 0 && isRunning.value) {
                delay(1000L)
                workoutTime.value--
            }
            isRunning.value = false
        }
    }

    fun pauseTimer() {
        isRunning.value = false
        timerJob?.cancel()
        timerJob = null
    }

    fun toggleTimer() {
        if (isRunning.value) {
            pauseTimer()
        } else {
            startTimer()
        }
    }

    fun toggleModal() {
        isModalClicked = !isModalClicked
    }

    fun setSelectedWorkout(workout: WorkoutResponseDto) {
        selectedWorkout.value = workout
        workoutTime.value = workout.duration * 60
        isRunning.value = false
    }

    fun updateModalClicked(value: Boolean) {
        isModalClicked = value
    }

    fun updateLevel(value: String) {
        level = value
    }

    fun fetchWorkoutList() = viewModelScope.launch {
        try {
            val response = workoutRepository.workoutList()
            Log.d("Workout", "서버 응답: $response")
            if (response.status == 200 || response.status == 201) {
                workoutList.value = response.data ?: emptyList()
            } else {
                isError.value = true
            }
        } catch (e: retrofit2.HttpException) {
            Log.e("Workout", "운동 불러오기 실패 ${e.code()}", e)
            isError.value = true
        } catch (e: Exception) {
            Log.e("Workout", "운동 불러오기 실패", e)
            isError.value = true
        } finally {
            isLoading.value = false
        }
    }
}
