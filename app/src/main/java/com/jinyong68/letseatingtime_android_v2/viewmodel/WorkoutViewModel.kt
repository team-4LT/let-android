package com.jinyong68.letseatingtime_android_v2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor() : ViewModel() {

    var isModalClicked by mutableStateOf(false)
        private set

    fun toggleModal() {
        isModalClicked = !isModalClicked
    }

    fun updateModalClicked(value: Boolean) {
        isModalClicked = value
    }

    var level by mutableStateOf("초급")
        private set

    fun updateLevel(value: String) {
        level = value
    }
}