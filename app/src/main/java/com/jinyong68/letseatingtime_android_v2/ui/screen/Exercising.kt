package com.jinyong68.letseatingtime_android_v2.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jinyong68.letseatingtime_android_v2.R
import com.jinyong68.letseatingtime_android_v2.ScreenNavigate
import com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.White
import com.jinyong68.letseatingtime_android_v2.ui.theme.pretendard

@Composable
fun ExercisingScreen(onMoveScreen : (String) -> Unit) {
    Box {
        Row {
            Image(
                modifier = Modifier.clickable {
                    onMoveScreen(ScreenNavigate.WORKOUT.name)
                },
                painter = painterResource(R.drawable.back),
                contentDescription = null
            )
            Text(
                text = "돌아가기",
                style = TextStyle(
                    fontFamily = pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp),
                )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Text(text = "가볍게 뛰며\n음식 소화를 시켜보아요!", style = AppTypography.displayMedium)
            Image(
                painter = painterResource(R.drawable.girl_running),
                contentDescription = null
            )
            Row {
                Image(
                    painter = painterResource(R.drawable.timer),
                    contentDescription = null
                )
                Text(text = "3:00", style = AppTypography.displayLarge)
            }
            Button(
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth(.8f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Main,
                    contentColor = White
                ),
                onClick = {},
            ) {
                Text(text = "시작하기", style = AppTypography.titleLarge)
            }
        }
    }

}