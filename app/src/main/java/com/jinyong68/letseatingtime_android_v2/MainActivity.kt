package com.jinyong68.letseatingtime_android_v2

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jinyong68.letseatingtime_android_v2.ui.screen.Home
import com.jinyong68.letseatingtime_android_v2.ui.screen.Login
import com.jinyong68.letseatingtime_android_v2.ui.screen.MealScan
import com.jinyong68.letseatingtime_android_v2.ui.screen.SignUp.SignUpIdStatus
import com.jinyong68.letseatingtime_android_v2.ui.screen.SignUp.SignUpInfoStatus
import com.jinyong68.letseatingtime_android_v2.ui.screen.SplashScreen
import com.jinyong68.letseatingtime_android_v2.ui.screen.Workout
import com.jinyong68.letseatingtime_android_v2.viewmodel.HomeViewModel
import com.jinyong68.letseatingtime_android_v2.viewmodel.LoginViewModel
import com.jinyong68.letseatingtime_android_v2.viewmodel.SignUpViewModel
import com.jinyong68.letseatingtime_android_v2.viewmodel.WorkoutViewModel

enum class ScreenNavigate {
    SPLASH,
    LOGIN,
    SIGNUPINFOSTATUS,
    SIGNUPIDSTATUS,
    HOME,
    MEALSCAN,
    WORKOUT
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
    fun enableOrientationSensor() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

    fun lockPortrait() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}
