package com.jinyong68.letseatingtime_android_v2.ui.component.questionnaire.worstMealVoteScreen

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.jinyong68.letseatingtime_android_v2.ScreenNavigate
import com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography
import com.jinyong68.letseatingtime_android_v2.ui.theme.Bg
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main2
import com.jinyong68.letseatingtime_android_v2.ui.theme.White
import com.jinyong68.letseatingtime_android_v2.viewmodel.QuestionnaireViewModel

@Composable
fun WorstMealVoteScreen (onMoveScreen: (String) -> Unit) {
    val viewModel : QuestionnaireViewModel = hiltViewModel()
    val mealList = viewModel.nowMeal.menus
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
                    text = "가장 불호였던",
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
                    mealList.forEachIndexed { manuId, menuName ->
                        val isSelected = manuId in selectedIndices
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
                                if (isSelected) selectedIndices.remove(manuId)
                                else selectedIndices.add(manuId)
                            }
                        ) {
                            Text(text = menuName.menuName, style = AppTypography.titleMedium)
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
                        containerColor = Main,
                        contentColor = White
                    ),
                    onClick = {
                        for (i in selectedIndices) {
                            viewModel.voteMineMenu(i)
                        }
                        onMoveScreen(ScreenNavigate.QUESTIONNAIREFINISH.name)
                    }
                ) {
                    Text(text = "다음", style = AppTypography.titleLarge)
                }
            }
        }

    }
}