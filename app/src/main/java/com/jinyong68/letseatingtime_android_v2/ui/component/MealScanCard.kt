package com.jinyong68.letseatingtime_android_v2.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jinyong68.letseatingtime_android_v2.R
import com.jinyong68.letseatingtime_android_v2.ScreenNavigate
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.White

@Composable
fun MealScanCard(
    onMoveScreen: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(
                indication = null, // ripple 효과 제거
                interactionSource = remember { MutableInteractionSource() } // 필수
            ) {
                onMoveScreen(ScreenNavigate.MEALSCAN.name)
            },

        ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.14f)
                .clip(RoundedCornerShape(8.dp))
                .background(Main)
                .padding(horizontal = 18.dp, vertical = 25.dp),

            ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically, // 이미지와 텍스트를 수직 가운데 정렬
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.photo),
                    contentDescription = "LET",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.width(24.dp).height(24.dp)
                )
                Column {
                    Text(
                        modifier = Modifier,
                        text = "인증사진 찍기",
                        color = White,
                        fontSize = 20.sp
                    )
                    Text(text = "인증사진을 올려주세요!", color = White, fontSize = 12.sp)
                }
            }
        }
    }
}