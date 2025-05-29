package com.jinyong68.letseatingtime_android_v2

import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavHostController
import com.jinyong68.letseatingtime_android_v2.ui.screen.QuestionnaireFinishScreen
import com.jinyong68.letseatingtime_android_v2.ui.screen.QuestionnaireScreen
import com.jinyong68.letseatingtime_android_v2.ui.theme.Bg

enum class ScreenNavigate {
    SPLASH,
    LOGIN,
    SIGNUPINFOSTATUS,
    SIGNUPIDSTATUS,
    HOME,
    WORKOUT,
    QUESTIONNAIRE,
    QUESTIONNAIREFINISH,
}

@Composable
fun App() {

    var currentRoute by remember { mutableStateOf(ScreenNavigate.HOME.name) }
    var showBottomNav by remember { mutableStateOf(true) }

    val navController = rememberNavController()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        Scaffold(
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = {
                if (showBottomNav) {
//                BottomNavigationBar(navController)
                }
            },
            containerColor = Bg
        ) { innerPadding ->
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = ScreenNavigate.QUESTIONNAIRE.name
            ) {
                composable(route = ScreenNavigate.SPLASH.name) {
                    SplashScreen(onMoveScreen = { destination -> navController.navigate(destination) })
                }
                composable(route = ScreenNavigate.LOGIN.name) {
                    val loginViewModel: LoginViewModel = hiltViewModel()
                    Login(
                        modifier = Modifier,
                        onMoveScreen = { destination -> navController.navigate(destination) },
                        viewModel = loginViewModel
                    )
                }
                composable(route = ScreenNavigate.SIGNUPIDSTATUS.name) {
                    val signUpViewModel: SignUpViewModel = hiltViewModel()
                    SignUpIdStatus(
                        modifier = Modifier,
                        onMoveScreen = { destination -> navController.navigate(destination) },
                        viewModel = signUpViewModel
                    )
                }
                composable(route = ScreenNavigate.SIGNUPINFOSTATUS.name) {
                    val signUpViewModel: SignUpViewModel = hiltViewModel()
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
                composable(route = ScreenNavigate.WORKOUT.name) {
                    Workout(
                        modifier = Modifier,
                        onMoveScreen = { destination -> navController.navigate(destination) },
                        viewModel = WorkoutViewModel()
                    )
                }
                composable(route = ScreenNavigate.QUESTIONNAIRE.name) {
                    QuestionnaireScreen(onMoveScreen = { destination ->
                        navController.navigate(
                            destination
                        )
                    })
                }
                composable(route = ScreenNavigate.QUESTIONNAIREFINISH.name) {
                    QuestionnaireFinishScreen(onMoveScreen = { destination ->
                        navController.navigate(
                            destination
                        )
                    })
                }
            }
        }
    }
}