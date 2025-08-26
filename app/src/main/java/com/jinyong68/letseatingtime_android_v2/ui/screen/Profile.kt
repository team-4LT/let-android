package com.jinyong68.letseatingtime_android_v2.ui.screen

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.jinyong68.letseatingtime_android_v2.R
import com.jinyong68.letseatingtime_android_v2.ScreenNavigate
import com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.White
import com.jinyong68.letseatingtime_android_v2.ui.theme.pretendard
import com.jinyong68.letseatingtime_android_v2.viewmodel.LoginViewModel
import com.jinyong68.letseatingtime_android_v2.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(onMoveScreen : (String) -> Unit, viewModel: ProfileViewModel) {

    val metrics = Resources.getSystem().displayMetrics
    val widthDp = (metrics.widthPixels / metrics.density)/2-85.toInt()
    val heightDp = (metrics.heightPixels / metrics.density)/100*15.toInt()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to Main,
                        0.25f to Color(0xFFFF8957)
                    )
                )
            ),
        contentAlignment = Alignment.BottomStart
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .zIndex(0f)
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = widthDp.dp, y = heightDp.dp)
                .zIndex(2f),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(R.drawable.df),
                contentDescription = null,
                modifier = Modifier
                    .size(170.dp)
            )
            Spacer(modifier = Modifier.padding(12.dp))
            Text(
                text = viewModel.me.value?.realName ?: "어떡해",
                    fontFamily = pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = "2학년 2반 6번", style = TextStyle(
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                ),
                textAlign = TextAlign.Center
            )
        }
        Box(
            // 큰 흰색 박스
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(White)
                .padding(vertical = 40.dp)
                .zIndex(1f),
            contentAlignment = Alignment.BottomCenter,
        )
        {
            Button(
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth(.8f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Main,
                    contentColor = White
                ),
                onClick = {onMoveScreen(ScreenNavigate.LOGIN.name)},
            ) {
                Text(text = "로그아웃", style = AppTypography.titleLarge)
            }
            Spacer(modifier = Modifier.height(96.dp))
        }
    }
}