package com.jinyong68.letseatingtime_android_v2.ui.screen

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jinyong68.letseatingtime_android_v2.ui.component.questionnaire.ProgressBar
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.jinyong68.letseatingtime_android_v2.ui.component.questionnaire.bestMealVoteScreen.BestMealVoteScreen
import com.jinyong68.letseatingtime_android_v2.ui.component.questionnaire.mealAmountCheck.MealAmountCheckScreen
import com.jinyong68.letseatingtime_android_v2.ui.component.questionnaire.rating.RatingScreen
import com.jinyong68.letseatingtime_android_v2.ui.component.questionnaire.worstMealVoteScreen.WorstMealVoteScreen
import com.jinyong68.letseatingtime_android_v2.viewmodel.HomeViewModel
import com.jinyong68.letseatingtime_android_v2.viewmodel.QuestionnaireViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime


@Composable
fun QuestionnaireScreen(
    onMoveScreen : (String) -> Unit,
) {
    val viewModel : QuestionnaireViewModel = hiltViewModel()
    var step = viewModel.step
    val visible = remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.fetchMenu()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        // ProgressBar with smooth animation
        ProgressBar(step = step)


        // Animated content using AnimatedVisibility
        AnimatedVisibility(
            visible = visible.value,
            enter = slideInHorizontally(
                animationSpec = tween(300)
            ) { fullWidth ->
                fullWidth // 👉 오른쪽에서 들어옴
            } + fadeIn(animationSpec = tween(300)),

            exit = slideOutHorizontally(
                animationSpec = tween(300)
            ) { fullWidth ->
                -fullWidth // 👉 왼쪽으로 나감
            } + fadeOut()
        ) {
            val onNext: () -> Unit = {
                visible.value = false
                coroutineScope.launch {
                    delay(300)
                    step++
                    visible.value = true
                }
            }

            when (step) {
                1 -> RatingScreen(onNext = {
                    visible.value = false
                    coroutineScope.launch {
                        delay(300)
                        viewModel.step = 2
                        visible.value = true
                    }
                })

                2 -> MealAmountCheckScreen(onNext = {
                    visible.value = false
                    coroutineScope.launch {
                        delay(300)
                        viewModel.step = 3
                        visible.value = true
                    }
                })

                3 -> BestMealVoteScreen(onNext = {
                    visible.value = false
                    coroutineScope.launch {
                        delay(300)
                        viewModel.step = 4
                        visible.value = true
                    }
                })
                4 -> WorstMealVoteScreen(onMoveScreen = onMoveScreen)
            }
        }
    }
}



@Preview
@Composable
fun Preview() {
    QuestionnaireScreen(onMoveScreen = {},)
}