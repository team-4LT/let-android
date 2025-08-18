package com.jinyong68.letseatingtime_android_v2.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jinyong68.letseatingtime_android_v2.ui.component.Workout.WorkoutCard
import com.jinyong68.letseatingtime_android_v2.ui.component.Workout.WorkoutModal
import com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography
import com.jinyong68.letseatingtime_android_v2.ui.theme.Bg
import com.jinyong68.letseatingtime_android_v2.ui.theme.Black
import com.jinyong68.letseatingtime_android_v2.viewmodel.WorkoutViewModel

@Composable
fun Workout(
    modifier: Modifier = Modifier,
    onMoveScreen: (String) -> Unit,
    viewModel: WorkoutViewModel
) {
    LaunchedEffect(Unit) {
//        viewModel.fetchWorkoutList()
    }
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp)
            .background(Bg)
            .statusBarsPadding(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Spacer(modifier = Modifier.height(0.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "운동",
                    style = AppTypography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    color = Black
                )
                Text(
                    text = "난이도 설정",
                    style = AppTypography.titleLarge,
                    color = Black,
                    modifier = modifier.clickable { viewModel.toggleModal() }
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState),
            ) {
                viewModel.workoutList.value.forEach { it ->
                    WorkoutCard(data = it)
                }
            }
            if (viewModel.isModalClicked) {
                WorkoutModal(
                    selectedLevel = viewModel.level,
                    onSelectLevel = {
                        viewModel.updateLevel(it)
                        viewModel.updateModalClicked(false)
                    },
                    onDismiss = {
                        viewModel.updateModalClicked(false)
                    }
                )
            }
        }
    }
}