package com.jinyong68.letseatingtime_android_v2.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Home(
    modifier : Modifier = Modifier,
    onMoveScreen : (String) -> Unit = {}
) {
    Text("안녕")
}