package com.jinyong68.letseatingtime_android_v2.ui.component.questionnaire.mealAmountCheck

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography
import com.jinyong68.letseatingtime_android_v2.ui.theme.Bg
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main2
import com.jinyong68.letseatingtime_android_v2.ui.theme.White

@Composable
fun MealAmountCheckScreen( onNext : () -> Unit ) {

    val mealAmountList = listOf("적음", "적당함", "많음")
    val selectedIndex = remember { mutableIntStateOf(-1) } // 아무것도 선택되지 않은 상태


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
                modifier = Modifier.weight(2f).fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    mealAmountList.forEachIndexed { index, text ->
                        Button(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(55.dp)
                                .border(BorderStroke(1.dp,Main2),RoundedCornerShape(8.dp)),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedIndex.intValue == index) Main2 else White,
                                contentColor = if (selectedIndex.intValue == index) White else Main2,
                            ),
                            onClick = { selectedIndex.intValue = index }
                        ) {
                            Text(text = text, style = AppTypography.titleMedium)
                        }
                    }
                }
            }
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                Button(
                    modifier = Modifier
                        .height(55.dp)
                        .fillMaxWidth(.8f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Main2,
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
fun Meal() {
    MealAmountCheckScreen(onNext = {}) // 미리보기에서는 빈 람다 전달
}