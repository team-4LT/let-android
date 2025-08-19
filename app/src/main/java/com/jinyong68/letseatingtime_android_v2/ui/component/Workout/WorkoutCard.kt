package com.jinyong68.letseatingtime_android_v2.ui.component.Workout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.jinyong68.letseatingtime_android_v2.R
import com.jinyong68.letseatingtime_android_v2.ScreenNavigate
import com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography
import com.jinyong68.letseatingtime_android_v2.ui.theme.Black
import com.jinyong68.letseatingtime_android_v2.ui.theme.White
import com.jinyong68.network.dto.WorkoutResponseDto
import com.jinyong68.letseatingtime_android_v2.viewmodel.WorkoutViewModel

@Composable
fun WorkoutCard(
    data: WorkoutResponseDto,
    onMoveScreen: (String) -> Unit,
    viewModel: WorkoutViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                viewModel.setSelectedWorkout(data)
                onMoveScreen(ScreenNavigate.EXERCISING.name)
            }
    ) {
        Image(
            painter = painterResource(
                if(data.category == "MOVING") R.drawable.running_banner
                else if (data.category == "STRETCH"){ R.drawable.stretch_banner}
                else{ R.drawable.etc_banner}),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Black.copy(alpha = 0.3f))
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = data.title,
                style = AppTypography.headlineMedium,
                color = White
            )
            Text(
                text = data.description,
                style = AppTypography.bodySmall,
                fontWeight = FontWeight.Light,
                color = White
            )
        }
    }
}
