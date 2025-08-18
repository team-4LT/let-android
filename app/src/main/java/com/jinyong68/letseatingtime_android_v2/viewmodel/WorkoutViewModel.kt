package com.jinyong68.letseatingtime_android_v2.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jinyong68.network.dto.WorkoutResponseDto
import com.jinyong68.network.workout.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
    // 현재 진행 시간
    val workoutTime = mutableStateOf(0)
    // 지금 진행 중인지 아닌지
    val isRunning = mutableStateOf(false)
    var isModalClicked by mutableStateOf(false)
        private set
    fun decreaseTime() {
        viewModelScope.launch {
            while (workoutTime.value > 0 && isRunning.value) {
                delay(1000L)
                workoutTime.value--
            }
            isRunning.value = false
        }
    }
    fun toggleModal() {
        isModalClicked = !isModalClicked
    }

    fun toggleIsRunning(){
        isRunning.value = !isRunning.value
    }

    fun setSelectedWorkout(workout: WorkoutResponseDto) {
        selectedWorkout.value = workout
        workoutTime.value =  workout.time
        isRunning.value = false
    }

    fun updateModalClicked(value: Boolean) {
        isModalClicked = value
    }

    var level by mutableStateOf("초급")
        private set

    fun updateLevel(value: String) {
        level = value
    }

    fun fetchWorkoutList() = viewModelScope.launch {
        try{
            val response = workoutRepository.workoutList()
            Log.d("Workout", "서버 응답: $response")

            if (response.status == 200 || response.status == 201) {
                workoutList.value = response.data ?: emptyList()
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
}