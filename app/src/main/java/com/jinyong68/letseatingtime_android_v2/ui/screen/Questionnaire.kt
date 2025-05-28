package com.jinyong68.letseatingtime_android_v2.ui.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jinyong68.letseatingtime_android_v2.ui.component.questionnaire.ProgressBar
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun QuestionnaireScreen() {
    var step by remember { mutableStateOf(1) }
    val visible = remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

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
            when (step) {
                1 -> RatingScreen(onNext = {
                    visible.value = false
                    // 애니메이션 후 step 변경
                    coroutineScope.launch {
                        delay(300)
                        step++
                        visible.value = true
                    }
                })

                2 -> Box {}

                3 -> Box{}
                else -> Box{}
            }
        }
    }
}



@Preview
@Composable
fun Preview() {
    QuestionnaireScreen()
}