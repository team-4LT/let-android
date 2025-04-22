package com.jinyong68.letseatingtime_android_v2.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jinyong68.letseatingtime_android_v2.ScreenNavigate

@Composable
fun Home(
    modifier: Modifier = Modifier,
    onMoveScreen: (String) -> Unit
) {
    Text(
        modifier = Modifier.fillMaxSize().statusBarsPadding().clickable{
            onMoveScreen(ScreenNavigate.MEALSCAN.name)
        },
        text = "안녕"
    )
}