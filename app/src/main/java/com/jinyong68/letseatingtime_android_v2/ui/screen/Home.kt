package com.jinyong68.letseatingtime_android_v2.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jinyong68.letseatingtime_android_v2.R
import com.jinyong68.letseatingtime_android_v2.ui.component.Home.MealScanCard
import com.jinyong68.letseatingtime_android_v2.ui.component.Home.MealTable
import com.jinyong68.letseatingtime_android_v2.ui.component.Home.WarningTable
import com.jinyong68.letseatingtime_android_v2.ui.component.Home.WorkoutTable
import com.jinyong68.letseatingtime_android_v2.ui.theme.Bg
import com.jinyong68.letseatingtime_android_v2.viewmodel.HomeViewModel
import com.jinyong68.letseatingtime_android_v2.viewmodel.WorkoutViewModel
import java.time.LocalDate

@Composable
fun Home(
    modifier: Modifier = Modifier,
    onMoveScreen: (String) -> Unit,
    viewModel: HomeViewModel,
    workoutViewModel: WorkoutViewModel
) {
    val clickedDay = remember { mutableStateOf(LocalDate.now().dayOfMonth) }
    val clickedDayOfWeek = remember { mutableStateOf(LocalDate.now().dayOfWeek) }
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .background(Bg)
            .statusBarsPadding()
            .verticalScroll(scrollState),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Spacer(modifier = Modifier.height(0.dp))
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "LET",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxHeight(0.05f)
                    .fillMaxWidth(0.2f)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
//                WarningTable(time = "점심", meal = "피자", allergy = "우유")
                MealScanCard(viewModel)
                MealTable(
                    viewModel = viewModel,
                    mealList = viewModel.mealList.value,
                    month = viewModel.month,
                    day = clickedDay.value,
                    firstDayOfMonth = viewModel.firstDayOfMonth,
                    getMaxDayOfMonth = viewModel.getMaxDayOfMonth(viewModel.year, viewModel.month),
                    clickedDay = clickedDay,
                    onClickDay = { clickedDay.value = it },
                    clickedDayOfWeek = clickedDayOfWeek,
                    onClickDayOfWeek = { clickedDayOfWeek.value = it }
                )
                WorkoutTable(data = viewModel.workoutRecommend.value, viewModel = workoutViewModel, onMoveScreen = onMoveScreen)
            }
            Spacer(
                modifier = modifier
                    .height(60.dp)
            )
        }
    }
    if (viewModel.modalVisibility.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(alpha = 0.5f))
                .clickable { viewModel.modalVisibility.value = false },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.check),
                        contentDescription = "nfc",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(48.dp)
                    )
                    androidx.compose.material3.Text(
                        text = "NFC 태그를 기기에 가까이 대주세요",
                        style = com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography.headlineSmall
                    )
                    androidx.compose.material3.Text(
                        text = "인식될 때까지 기다려주세요",
                        style = com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography.bodyMedium
                    )
                }
            }
        }
    }
}
