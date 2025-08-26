package com.jinyong68.letseatingtime_android_v2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navigation
import com.jinyong68.letseatingtime_android_v2.ui.component.BottomNavigationBar
import com.jinyong68.letseatingtime_android_v2.ui.screen.*
import com.jinyong68.letseatingtime_android_v2.ui.screen.SignUp.*
import com.jinyong68.letseatingtime_android_v2.ui.theme.Bg
import com.jinyong68.letseatingtime_android_v2.viewmodel.*

enum class ScreenNavigate {
    SPLASH,
    LOGIN,
    SIGNUPINFOSTATUS,
    SIGNUPIDSTATUS,
    SIGNUPALLERGYSTATUS,
    HOME,
    WORKOUT,
    QUESTIONNAIRE,
    QUESTIONNAIREFINISH,
    PROFILE,
    EXERCISING,
}

@Composable
fun App(navController: NavHostController) {
    var showBottomNav by remember { mutableStateOf(true) }

    val workoutViewModel: WorkoutViewModel = hiltViewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Bg,
        bottomBar = {
            if (showBottomNav) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination?.route
                val noBottomBarRoutes = listOf(
                    ScreenNavigate.QUESTIONNAIRE.name,
                    ScreenNavigate.QUESTIONNAIREFINISH.name,
                    ScreenNavigate.LOGIN.name,
                    ScreenNavigate.SIGNUPIDSTATUS.name,
                    ScreenNavigate.SIGNUPINFOSTATUS.name,
                    ScreenNavigate.SIGNUPALLERGYSTATUS.name,
                    ScreenNavigate.EXERCISING.name,
                    ScreenNavigate.SPLASH.name
                )
                if (currentDestination !in noBottomBarRoutes) {
                    BottomNavigationBar(navController)
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenNavigate.SPLASH.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            navigation(
                startDestination = ScreenNavigate.SIGNUPIDSTATUS.name,
                route = "signup_graph"
            ) {
                composable(ScreenNavigate.SIGNUPIDSTATUS.name) {
                    val parentEntry = remember(navController.currentBackStackEntry) {
                        navController.getBackStackEntry("signup_graph")
                    }
                    val signUpViewModel: SignUpViewModel = hiltViewModel(parentEntry)
                    SignUpIdStatus(
                        viewModel = signUpViewModel,
                        onMoveScreen = { destination ->
                            when (destination) {
                                "BACK" -> navController.popBackStack()
                                else -> navController.navigate(destination)
                            }
                        }
                    )
                }

                composable(ScreenNavigate.SIGNUPALLERGYSTATUS.name) {
                    val parentEntry = remember(navController.currentBackStackEntry) {
                        navController.getBackStackEntry("signup_graph")
                    }
                    val signUpViewModel: SignUpViewModel = hiltViewModel(parentEntry)
                    SignUpAllergyStatus(
                        viewModel = signUpViewModel,
                        onMoveScreen = { destination ->
                            when (destination) {
                                "BACK" -> navController.popBackStack()
                                else -> navController.navigate(destination)
                            }
                        }
                    )
                }

                composable(ScreenNavigate.SIGNUPINFOSTATUS.name) {
                    val parentEntry = remember(navController.currentBackStackEntry) {
                        navController.getBackStackEntry("signup_graph")
                    }
                    val signUpViewModel: SignUpViewModel = hiltViewModel(parentEntry)
                    SignUpInfoStatus(
                        viewModel = signUpViewModel,
                        onMoveScreen = { destination ->
                            when (destination) {
                                "BACK" -> navController.popBackStack()
                                else -> navController.navigate(destination)
                            }
                        }
                    )
                }
            }

            // 일반 화면
            composable(ScreenNavigate.SPLASH.name) {
                val splashViewModel: SplashViewModel = hiltViewModel()
                SplashScreen(
                    viewModel = splashViewModel,
                    onMoveScreen = { destination -> navController.navigate(destination) }
                )
            }

            composable(ScreenNavigate.LOGIN.name) {
                val loginViewModel: LoginViewModel = hiltViewModel()
                Login(
                    modifier = Modifier,
                    onMoveScreen = { destination -> navController.navigate(destination) },
                    viewModel = loginViewModel
                )
            }

            composable(ScreenNavigate.HOME.name) {
                val homeViewModel: HomeViewModel = hiltViewModel()
                Home(
                    modifier = Modifier,
                    onMoveScreen = { destination -> navController.navigate(destination) },
                    viewModel = homeViewModel,
                    workoutViewModel = workoutViewModel
                )
            }

            composable(ScreenNavigate.WORKOUT.name) {
                Workout(
                    modifier = Modifier,
                    onMoveScreen = { destination -> navController.navigate(destination) },
                    viewModel = workoutViewModel
                )
            }

            composable(ScreenNavigate.QUESTIONNAIRE.name) {
                QuestionnaireScreen(onMoveScreen = { destination -> navController.navigate(destination) })
            }

            composable(ScreenNavigate.QUESTIONNAIREFINISH.name) {
                QuestionnaireFinishScreen(onMoveScreen = { destination -> navController.navigate(destination) })
            }

            composable(ScreenNavigate.PROFILE.name) {
                val profileViewModel: ProfileViewModel = hiltViewModel()
                ProfileScreen(
                    onMoveScreen = { destination -> navController.navigate(destination) },
                    viewModel = profileViewModel
                )
            }

            composable(ScreenNavigate.EXERCISING.name) {
                ExercisingScreen(
                    onMoveScreen = { route -> navController.navigate(route) },
                    viewModel = workoutViewModel
                )
            }
        }
    }
}
