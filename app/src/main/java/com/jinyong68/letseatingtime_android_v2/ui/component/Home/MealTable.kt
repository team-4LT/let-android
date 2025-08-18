package com.jinyong68.letseatingtime_android_v2.ui.component.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jinyong68.letseatingtime_android_v2.R
import com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography
import com.jinyong68.letseatingtime_android_v2.ui.theme.Black
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main2
import com.jinyong68.letseatingtime_android_v2.ui.theme.White
import com.jinyong68.letseatingtime_android_v2.viewmodel.HomeViewModel
import com.jinyong68.network.dto.MealResponseDto
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun MealTable(
    viewModel: HomeViewModel,
    mealList: List<MealResponseDto>,
    month: Int,
    day: Int,
    firstDayOfMonth: LocalDate,
    getMaxDayOfMonth: Int,
    clickedDay: MutableState<Int>,
    onClickDay: (Int) -> Unit,
    clickedDayOfWeek: MutableState<DayOfWeek>,
    onClickDayOfWeek: (DayOfWeek) -> Unit
) {
    val scrollState = rememberScrollState()
    val currentDate = LocalDate.now()

    LaunchedEffect(Unit) {
        val itemWidthWithPadding = 80
        val targetScrollOffset = (clickedDay.value - 1) * itemWidthWithPadding
        scrollState.animateScrollTo(targetScrollOffset)
    }

    val koreanWeekDay = when (clickedDayOfWeek.value) {
        DayOfWeek.MONDAY -> "월"
        DayOfWeek.TUESDAY -> "화"
        DayOfWeek.WEDNESDAY -> "수"
        DayOfWeek.THURSDAY -> "목"
        DayOfWeek.FRIDAY -> "금"
        DayOfWeek.SATURDAY -> "토"
        DayOfWeek.SUNDAY -> "일"
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(400.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(White)
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "${month}월 ${day}일 ($koreanWeekDay)",
                style = AppTypography.headlineMedium,
                fontWeight = FontWeight.SemiBold
            )
        }

        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .fillMaxWidth()
                .background(color = Color(0xFFF5F5F5))
                .padding(horizontal = 8.dp)
        ) {
            for (i in 1..getMaxDayOfMonth) {
                val date = firstDayOfMonth.plusDays((i - 1).toLong())
                val weekDay = when (date.dayOfWeek) {
                    DayOfWeek.MONDAY -> "월"
                    DayOfWeek.TUESDAY -> "화"
                    DayOfWeek.WEDNESDAY -> "수"
                    DayOfWeek.THURSDAY -> "목"
                    DayOfWeek.FRIDAY -> "금"
                    DayOfWeek.SATURDAY -> "토"
                    DayOfWeek.SUNDAY -> "일"
                }

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            onClickDay(i)
                            onClickDayOfWeek(date.dayOfWeek)
                            viewModel.fetchMenu("${viewModel.year}-${String.format("%02d", viewModel.month)}-${String.format("%02d", i)}")
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                            .background(
                                color = if (day == i) Main2 else Color(0xFFF5F5F5),
                                shape = RoundedCornerShape(50.dp)
                            ),
                        textAlign = TextAlign.Center,
                        text = i.toString(),
                        style = AppTypography.headlineSmall,
                        color = if (day == i) White else Black
                    )
                    Text(
                        text = weekDay,
                        style = AppTypography.bodySmall,
                        color = Black
                    )
                }
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            for (i in 0..2) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth(0.9f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.width(65.dp)
                    ) {
                        Image(
                            painter = painterResource(
                                when (i) {
                                    0 -> R.drawable.breakfast
                                    1 -> R.drawable.lunch
                                    else -> R.drawable.dinner
                                }
                            ),
                            contentDescription = null,
                            modifier = Modifier.width(24.dp)
                        )
                        Text(
                            text = when (i) {
                                0 -> "아침"
                                1 -> "점심"
                                else -> "저녁"
                            },
                            style = AppTypography.titleLarge,
                            color = Black
                        )
                    }

                    Text(
                        text = mealList.getOrNull(i)?.menus?.joinToString(", ") { it.menuName } ?: "급식이 없습니다.",
                        style = AppTypography.bodySmall,
                        color = Black
                    )
                }
            }
        }
    }
}
