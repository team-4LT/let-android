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
import com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography
import com.jinyong68.letseatingtime_android_v2.ui.theme.Black
import com.jinyong68.letseatingtime_android_v2.ui.theme.White

@Composable
fun WorkoutTable(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .clip(RoundedCornerShape(8.dp))
            .padding(vertical = 20.dp),
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
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ){ // 카드 Column
            WorkoutCard(
                img = R.drawable.running_banner,
                title = "달리기 3분 뛰기",
                description = "달리기 10분을 뜁니다."
            )
            WorkoutCard(
                img = R.drawable.running_banner,
                title = "달리기 3분 뛰기",
                description = "달리기 10분을 뜁니다."
            )
            WorkoutCard(
                img = R.drawable.running_banner,
                title = "달리기 3분 뛰기",
                description = "달리기 10분을 뜁니다."
            )
        }
    }
}