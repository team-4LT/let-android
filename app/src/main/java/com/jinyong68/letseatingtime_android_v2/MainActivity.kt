package com.jinyong68.letseatingtime_android_v2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jinyong68.letseatingtime_android_v2.ui.screen.Home
import com.jinyong68.letseatingtime_android_v2.ui.screen.Login
import com.jinyong68.letseatingtime_android_v2.ui.screen.SignUp.SignUpIdStatus
import com.jinyong68.letseatingtime_android_v2.ui.screen.SignUp.SignUpInfoStatus

enum class ScreenNavigate {
    LOGIN,
    SIGNUPINFOSTATUS,
    SIGNUPIDSTATUS,
    HOME
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = ScreenNavigate.LOGIN.name) {
                composable(route = ScreenNavigate.LOGIN.name) {
                    Login(modifier = Modifier, onMoveScreen = { destination -> navController.navigate(destination)})
                }
                composable(route = ScreenNavigate.SIGNUPIDSTATUS.name) {
                    SignUpIdStatus(modifier = Modifier, onMoveScreen = {destination -> navController.navigate(destination)})
                }
                composable(route = ScreenNavigate.SIGNUPINFOSTATUS.name) {
                    SignUpInfoStatus(modifier = Modifier, onMoveScreen = { destination -> navController.navigate(destination)})
                }
                composable(route = ScreenNavigate.HOME.name) {
                    Home(modifier = Modifier, onMoveScreen = { destination -> navController.navigate(destination)})
                }
            }
        }
    }
}
