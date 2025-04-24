package com.jinyong68.letseatingtime_android_v2.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jinyong68.letseatingtime_android_v2.R
import com.jinyong68.letseatingtime_android_v2.ScreenNavigate
import com.jinyong68.letseatingtime_android_v2.ui.component.MealScanCard
import com.jinyong68.letseatingtime_android_v2.ui.theme.Bg
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.White

@Composable
fun Home(
    modifier: Modifier = Modifier,
    onMoveScreen: (String) -> Unit
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Bg)
            .statusBarsPadding()
            .verticalScroll(scrollState),
        contentAlignment = Alignment.TopCenter

    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Spacer(modifier = Modifier.height(0.dp))
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "LET",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxHeight(0.05f).fillMaxWidth(0.2f)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                 MealScanCard(onMoveScreen)
            }
        }
    }
}

@Preview
@Composable
fun HomeScreen() {
    Home(modifier = Modifier, onMoveScreen = {})
}