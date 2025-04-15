package com.jinyong68.letseatingtime_android_v2.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jinyong68.letseatingtime_android_v2.ui.compose.LoginTextField
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.White


@Composable
fun Login(
    modifier : Modifier = Modifier,
) {
    val idText: MutableState<String> = rememberSaveable {
        mutableStateOf(
            ""
        )
    }

    Box(
        modifier = modifier.fillMaxSize().background(
            brush = Brush.verticalGradient( // ✅ 세로 방향 그라데이션
                colorStops = arrayOf(
                    0.0f to Main, // 연한 회색
                    0.25f to Color(0xFFFF8957)  // 진한 회색
                )
            )
        ),
        contentAlignment = Alignment.BottomStart
    ){
        Box(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(White),
            contentAlignment = Alignment.Center
        ){
            LoginTextField(
                modifier = modifier,
                text = idText,
                placeholder = {Text("아이디를 입력해주세요")}
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun View(
    modifier: Modifier = Modifier
) {
    Box(

    ) {
        Login(modifier = modifier)
    }

}