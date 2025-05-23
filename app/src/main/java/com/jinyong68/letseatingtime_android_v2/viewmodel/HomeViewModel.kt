package com.jinyong68.letseatingtime_android_v2.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _date = LocalDate.now()
    val year = _date.year
    val month = _date.monthValue
    val day = _date.dayOfMonth
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
}

