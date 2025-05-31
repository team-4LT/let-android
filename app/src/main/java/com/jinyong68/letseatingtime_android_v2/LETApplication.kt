package com.jinyong68.letseatingtime_android_v2

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LETApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }


    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var context: Context

        fun getContext() = context
    }
}