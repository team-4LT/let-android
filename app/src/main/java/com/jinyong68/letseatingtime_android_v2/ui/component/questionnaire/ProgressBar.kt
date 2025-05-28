package com.jinyong68.letseatingtime_android_v2.ui.component.questionnaire

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.jinyong68.letseatingtime_android_v2.ui.theme.Grey
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.White
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.runtime.getValue


@Composable
fun ProgressBar(step: Int) {
    // 목표 progress 값 계산 (1/3, 2/3, 1.0)
    val targetProgress = step / 4f

    // 애니메이션으로 progress 부드럽게 전환
    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
        label = "ProgressAnimation"
    )

    LinearProgressIndicator(
        progress = { animatedProgress },
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp),
        color = Main,
        trackColor = White,
        strokeCap = StrokeCap.Square
    )
}
