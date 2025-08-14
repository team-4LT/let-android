package com.jinyong68.network.dto

data class BaseResponseDto<T>(
    val status: Int,
    val message: String,
    val data: T?
)
