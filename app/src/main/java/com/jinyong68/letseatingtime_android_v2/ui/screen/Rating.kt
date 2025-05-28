package com.jinyong68.letseatingtime_android_v2.ui.screen

import androidx.compose.foundation.Image
import com.jinyong68.letseatingtime_android_v2.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jinyong68.letseatingtime_android_v2.ui.component.questionnaire.rating.RatingStar
import com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography
import com.jinyong68.letseatingtime_android_v2.ui.theme.Bg
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.White

@Composable
fun RatingScreen(onNext : () -> Unit ) {

    var currentRating by remember { mutableIntStateOf(0) }

    Box(
        Modifier.fillMaxSize().background(Bg)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth()
            ){
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "오늘의 급식은 어땠나요?",
                    style = AppTypography.displayMedium,
                    textAlign = TextAlign.Center
                )
            }
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth()
            ) {
                RatingStar(
                    rating = currentRating,
                    modifier = Modifier.align(Alignment.Center),
                    onRatingChanged = { newRating ->
                        currentRating = newRating
                    }
                )
            }
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    modifier = Modifier.height(55.dp).fillMaxWidth(.8f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Main,
                        contentColor = White
                    ),
                    onClick = onNext,
                ) {
                    Text(text = "다음", style = AppTypography.titleLarge)
                }
            }


        }
    }

}

@Preview
@Composable
fun rating() {
    RatingScreen(onNext = {}) // 미리보기에서는 빈 람다 전달
}