package com.jinyong68.letseatingtime_android_v2.ui.component.questionnaire.bestMealVoteScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography
import com.jinyong68.letseatingtime_android_v2.ui.theme.Bg
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main2
import com.jinyong68.letseatingtime_android_v2.ui.theme.White

@Composable
fun BestMealVoteScreen(onNext: () -> Unit) {
    val mealList = listOf("흑미밥", "맑은한우샤브샤브", "봉추찜닭", "열무된장무침", "배추김치", "바이오제로요구르트")
    val selectedIndices = remember { mutableStateListOf<Int>() }

    Box(
        Modifier.fillMaxSize().background(Bg)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // 상단 영역 (1)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = "가장 맛있었던",
                    style = AppTypography.displayMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier,
                    text = "급식은 무엇인가요?",
                    style = AppTypography.displayMedium,
                    textAlign = TextAlign.Center
                )
            }

            // 중간 영역 (2) - 내용이 많으면 확장
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f, fill = false) // fill = false로 wrapContentHeight처럼 작동
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    mealList.forEachIndexed { index, text ->
                        val isSelected = index in selectedIndices
                        Button(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(55.dp)
                                .border(BorderStroke(1.dp, Main2), RoundedCornerShape(8.dp)),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isSelected) Main2 else White,
                                contentColor = if (isSelected) White else Main2,
                            ),
                            onClick = {
                                if (isSelected) selectedIndices.remove(index)
                                else selectedIndices.add(index)
                            }
                        ) {
                            Text(text = text, style = AppTypography.titleMedium)
                        }
                    }
                }
            }

            // 하단 영역 (1) - 최소 1 비율 유지, 내용 많으면 줄어듦
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Button(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .height(55.dp)
                        .fillMaxWidth(0.8f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Main2,
                        contentColor = White
                    ),
                    onClick = {
                        val selectedMeals = selectedIndices.map { mealList[it] }
                        onNext()
                    }
                ) {
                    Text(text = "다음", style = AppTypography.titleLarge)
                }
            }
        }

    }
}
