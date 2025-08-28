package com.jinyong68.letseatingtime_android_v2.viewmodel

import androidx.lifecycle.ViewModel
import com.jinyong68.data.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import android.util.Log

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenManager: TokenManager
) : ViewModel() {
    val accessToken = tokenManager.getAccessToken()
}