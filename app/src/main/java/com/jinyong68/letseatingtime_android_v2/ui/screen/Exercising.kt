package com.jinyong68.letseatingtime_android_v2.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.jinyong68.letseatingtime_android_v2.R
import com.jinyong68.letseatingtime_android_v2.ScreenNavigate
import com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography
import com.jinyong68.letseatingtime_android_v2.ui.theme.Bg
import com.jinyong68.letseatingtime_android_v2.ui.theme.Black
import com.jinyong68.letseatingtime_android_v2.ui.theme.DarkGray
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.White
import com.jinyong68.letseatingtime_android_v2.ui.theme.pretendard
import com.jinyong68.letseatingtime_android_v2.viewmodel.WorkoutViewModel

@Composable
fun ExercisingScreen(
    onMoveScreen : (String) -> Unit,
    viewModel: WorkoutViewModel
) {

    val time = viewModel.workoutTime.value ?: 0
    val minutes = time / 60
    val seconds = time % 60

    // 모달 박스
    if(viewModel.workoutTime.value == 0) {
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
                .background(color = Color(0xFF2A2B2C).copy(alpha = 0.40f))
                .zIndex(5000f)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {}
        ) {
            // 모달
            Column(
                modifier = Modifier.fillMaxWidth(0.9f).background(White, shape = RoundedCornerShape(16.dp)).padding(horizontal = 28.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    Text("축하해요! 🎉", color = Color(0xFF969696), style = AppTypography.titleLarge)
                    Text("운동루틴을 완료했어요!", color = Black, style = AppTypography.headlineMedium)
                }
                Text(
                    text = buildAnnotatedString {
                        append("총 ")
                        withStyle(style = SpanStyle(
                            color = Main,
                            fontWeight = FontWeight.SemiBold
                        )) {
                            val t = viewModel.selectedWorkout.value?.duration ?: 0
                            append("${(t / 60).toString().padStart(2,'0')}:${(t % 60).toString().padStart(2,'0')} 동안")
                        }
                        append("\n뛰었어요")
                    },
                    style = AppTypography.displaySmall,
                    textAlign = TextAlign.Center
                )
                Button(
                    modifier = Modifier
                        .height(44.dp)
                        .fillMaxWidth()
                        .border(width = 1.dp , if(viewModel.isRunning.value){Main}else{Bg}, shape = RoundedCornerShape(8.dp)),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =Main,
                        contentColor = White
                    ),
                    onClick = {
                        onMoveScreen(ScreenNavigate.HOME.name) },
                ) {
                    Text(text = "확인", style = AppTypography.titleLarge, color =White)
                }
            }
        }
    }


    // 여기가 본격적인 곳
    Box(
        modifier = Modifier.fillMaxSize().background(Bg).statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(36.dp),
        ) {
            Spacer(Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.clickable{
                        onMoveScreen(ScreenNavigate.WORKOUT.name)
                    },
                    painter = painterResource(R.drawable.back),
                    contentDescription = null
                )
                Text(
                    text = "돌아가기",
                    style = TextStyle(
                        fontFamily = pretendard,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    ),
                )
            }
            // main wrap
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ){
                Spacer(Modifier.height(8.dp))
                Column {
                    Text(
                        text = viewModel.selectedWorkout.value?.title ?: "알 수 없는 운동",
                        style = AppTypography.titleLarge,
                        color = DarkGray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(0.9f)
                    )
                    Text(
                        text = viewModel.selectedWorkout.value?.method ?: "시작하기를 눌러 운동을 시작하세요!",
                        style = AppTypography.displayMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(0.9f)
                    )
                }
                Image(
                    modifier = Modifier.size(250.dp),
                    painter = painterResource(R.drawable.girl_running),
                    contentDescription = null
                )
                Row (
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(R.drawable.timer),
                        contentDescription = null
                    )

                    Text(
                        text = "${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}",
                        style = AppTypography.displayLarge
                    )
                }
                Button(
                    modifier = Modifier
                        .height(55.dp)
                        .fillMaxWidth(.8f)
                        .border(width = 1.dp , if(viewModel.isRunning.value){Main}else{Bg}, shape = RoundedCornerShape(8.dp)),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if(viewModel.isRunning.value){Bg}else{Main},
                        contentColor = if(viewModel.isRunning.value){Main}else{White}
                    ),
                    onClick = {
                        viewModel.toggleTimer() },
                ) {
                    Text(text = if(viewModel.isRunning.value){"멈추기"}else{"시작하기"}, style = AppTypography.titleLarge, color = if(viewModel.isRunning.value){Main}else{White})
                }
            }
        }
    }
}
