package com.jinyong68.letseatingtime_android_v2

import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jinyong68.letseatingtime_android_v2.ui.screen.Home
import com.jinyong68.letseatingtime_android_v2.ui.screen.Login
import com.jinyong68.letseatingtime_android_v2.ui.screen.SignUp.SignUpIdStatus
import com.jinyong68.letseatingtime_android_v2.ui.screen.SignUp.SignUpInfoStatus
import com.jinyong68.letseatingtime_android_v2.ui.screen.SplashScreen
import com.jinyong68.letseatingtime_android_v2.ui.screen.Workout
import com.jinyong68.letseatingtime_android_v2.viewmodel.HomeViewModel
import com.jinyong68.letseatingtime_android_v2.viewmodel.LoginViewModel
import com.jinyong68.letseatingtime_android_v2.viewmodel.SignUpViewModel
import com.jinyong68.letseatingtime_android_v2.viewmodel.WorkoutViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.jinyong68.letseatingtime_android_v2.ui.screen.QuestionnaireScreen

enum class ScreenNavigate {
    SPLASH,
    LOGIN,
    SIGNUPINFOSTATUS,
    SIGNUPIDSTATUS,
    HOME,
    WORKOUT,
    QUESTIONNAIRE,
}

@Composable
fun App() {
    val navController = rememberNavController()



    NavHost(navController = navController, startDestination = ScreenNavigate.QUESTIONNAIRE.name) {
        composable(route = ScreenNavigate.SPLASH.name) {
            SplashScreen(onMoveScreen = { destination -> navController.navigate(destination) })
        }
        composable(route = ScreenNavigate.LOGIN.name) {
            val loginViewModel : LoginViewModel = hiltViewModel()
            Login(
                modifier = Modifier,
                onMoveScreen = { destination -> navController.navigate(destination) },
                viewModel = loginViewModel
            )
        }
        composable(route = ScreenNavigate.SIGNUPIDSTATUS.name) {
            val signUpViewModel : SignUpViewModel = hiltViewModel()
            SignUpIdStatus(
                modifier = Modifier,
                onMoveScreen = { destination -> navController.navigate(destination) },
                viewModel = signUpViewModel
            )
        }
        composable(route = ScreenNavigate.SIGNUPINFOSTATUS.name) {
            val signUpViewModel : SignUpViewModel = hiltViewModel()
            SignUpInfoStatus(
                modifier = Modifier,
                onMoveScreen = { destination -> navController.navigate(destination) },
                viewModel = signUpViewModel
            )
        }
        composable(route = ScreenNavigate.HOME.name) {
            Home(
                modifier = Modifier,
                onMoveScreen = { destination -> navController.navigate(destination) },
                viewModel = HomeViewModel()
            )
        }
        composable (route = ScreenNavigate.WORKOUT.name){
            Workout(
                modifier = Modifier,
                onMoveScreen = {destination -> navController.navigate(destination)},
                viewModel = WorkoutViewModel()
            )
        }
        composable (route = ScreenNavigate.QUESTIONNAIRE.name) {
            QuestionnaireScreen(onMoveScreen = { destination -> navController.navigate(destination)})
        }

    }
}