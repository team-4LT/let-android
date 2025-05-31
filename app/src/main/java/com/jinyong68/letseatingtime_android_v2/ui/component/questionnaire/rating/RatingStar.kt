package com.jinyong68.letseatingtime_android_v2.ui.component.questionnaire.rating

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jinyong68.letseatingtime_android_v2.R

@Composable
fun RatingStar(
    rating: Int,
    onRatingChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val filledStar = painterResource(R.drawable.star_yellow)
    val emptyStar = painterResource(R.drawable.star_gray)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        for (i in 1..5) {
            Image(
                painter = if (i <= rating) filledStar else emptyStar,
                contentDescription = "별 $i",
                modifier = Modifier
                    .size(58.dp)
                    .clickable(
                        // 클릭 효과 제거
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onRatingChanged(i)
                    },
                contentScale = ContentScale.Fit
            )
        }
    }
}
