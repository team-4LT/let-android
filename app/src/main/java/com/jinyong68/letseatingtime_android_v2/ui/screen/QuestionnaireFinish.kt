package com.jinyong68.letseatingtime_android_v2.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jinyong68.letseatingtime_android_v2.ScreenNavigate
import com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography
import com.jinyong68.letseatingtime_android_v2.ui.theme.Bg
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main2
import com.jinyong68.letseatingtime_android_v2.ui.theme.White

@Composable
fun QuestionnaireFinishScreen(onMoveScreen : (String) -> Unit) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Bg)
            .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ){
                Text(text = "설문에 참여해주셔서 감사합니다")
                Button(
                    modifier = Modifier
                        .height(55.dp)
                        .fillMaxWidth(.8f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Main2,
                        contentColor = White
                    ),
                    onClick = {onMoveScreen(ScreenNavigate.HOME.name)},
                ) {
                    Text(text = "홈으로", style = AppTypography.titleLarge)
                }
            }
        }
}