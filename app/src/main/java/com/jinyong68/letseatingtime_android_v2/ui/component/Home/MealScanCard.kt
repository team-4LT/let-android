package com.jinyong68.letseatingtime_android_v2.ui.component.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jinyong68.letseatingtime_android_v2.R
import com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography
import com.jinyong68.letseatingtime_android_v2.ui.theme.Gray
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.White
import com.jinyong68.letseatingtime_android_v2.viewmodel.HomeViewModel


@Composable
fun MealScanCard(
    viewModel: HomeViewModel
) {
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable{if (!viewModel.isAttend.value){viewModel.isAttend.value = true}}
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.14f)
                .clip(RoundedCornerShape(8.dp))
                .background(if(viewModel.isAttend.value){
                    Gray
                }else{Main})
                .padding(horizontal = 16.dp, vertical = 24.dp),

            ) {
            // 출석체크 박스
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.check),
                    contentDescription = "check",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.width(24.dp)
                )
                Column {
                    Text(
                        modifier = Modifier,
                        text = if(viewModel.isAttend.value){"인증 완료!"}else{"출석체크 하기"},
                        color = White,
                        style = AppTypography.headlineMedium,
                    )
                    Text(
                        text = if(viewModel.isAttend.value){"급식 인증을 완료했습니다."}else{"NFC 태그를 찍어주세요!"},
                        color = White,
                        style = AppTypography.bodySmall
                        )
                }
            }
        }
    }
}