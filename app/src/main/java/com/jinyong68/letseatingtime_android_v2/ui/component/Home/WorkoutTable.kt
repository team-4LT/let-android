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
import androidx.hilt.navigation.compose.hiltViewModel
import com.jinyong68.letseatingtime_android_v2.ui.component.Workout.WorkoutCard
import com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography
import com.jinyong68.letseatingtime_android_v2.ui.theme.Black
import com.jinyong68.letseatingtime_android_v2.ui.theme.White
import com.jinyong68.letseatingtime_android_v2.viewmodel.HomeViewModel
import com.jinyong68.letseatingtime_android_v2.viewmodel.WorkoutViewModel
import com.jinyong68.network.dto.Exercise
import com.jinyong68.network.dto.WorkoutResponseDto

@Composable
fun WorkoutTable(
    data: List<Exercise>,
    viewModel: WorkoutViewModel,
    onMoveScreen: (String) -> Unit
){
    val homeViewModel : HomeViewModel = hiltViewModel()
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
                text = homeViewModel.message.value,
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
                val exercise = WorkoutResponseDto(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    method = it.method,
                    calorie = it.expected_calories,
                    duration = it.recommended_duration,
                    category = it.category,
                )
                WorkoutCard(
                    data = exercise,
                    viewModel = viewModel,
                    onMoveScreen = onMoveScreen
                )
            }
        }
    }
}