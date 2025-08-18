package com.jinyong68.letseatingtime_android_v2.ui.component.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jinyong68.letseatingtime_android_v2.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jinyong68.letseatingtime_android_v2.ui.component.Workout.WorkoutCard
import com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography
import com.jinyong68.letseatingtime_android_v2.ui.theme.Black
import com.jinyong68.letseatingtime_android_v2.ui.theme.White
import com.jinyong68.letseatingtime_android_v2.viewmodel.WorkoutViewModel
import com.jinyong68.network.dto.WorkoutResponseDto

@Composable
fun WorkoutTable(
    data: List<WorkoutResponseDto>,
    viewModel: WorkoutViewModel,
    onMoveScreen: (String) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = "운동 추천",
                style = AppTypography.headlineMedium,
                fontWeight = FontWeight.SemiBold,
                color = Black
            )
            Text(
                text = "오늘은 00kcal섭취 하셨네요! 이 운동을 통해 00만큼 운동해 보아요!",
                style = AppTypography.labelLarge,
                color = Black
            )
        }
        Column (
            modifier = Modifier
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){ // 카드 Column
            data.forEach { it ->
                WorkoutCard(
                    data = it,
                    viewModel = viewModel,
                    onMoveScreen = onMoveScreen
                )
            }
        }
    }
}