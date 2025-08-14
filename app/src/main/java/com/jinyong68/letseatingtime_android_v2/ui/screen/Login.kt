package com.jinyong68.letseatingtime_android_v2.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.jinyong68.letseatingtime_android_v2.R
import com.jinyong68.letseatingtime_android_v2.ScreenNavigate
import com.jinyong68.letseatingtime_android_v2.ui.component.ButtonField
import com.jinyong68.letseatingtime_android_v2.ui.component.LoginButton
import com.jinyong68.letseatingtime_android_v2.ui.component.TextField.LoginTextField
import com.jinyong68.letseatingtime_android_v2.ui.theme.Gray
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.Placeholder
import com.jinyong68.letseatingtime_android_v2.ui.theme.White
import com.jinyong68.letseatingtime_android_v2.viewmodel.LoginViewModel


@Composable
fun Login(
    modifier: Modifier = Modifier,
    onMoveScreen: (String) -> Unit = {},
    viewModel: LoginViewModel
) {
    val idText = rememberSaveable { mutableStateOf("") }
    val pwText = rememberSaveable { mutableStateOf("") }
    Box(
        modifier = modifier
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
                .imePadding()
                .align(Alignment.TopCenter)
        )

        Box(
            // 큰 흰색 박스
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.77f)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(White)
                .padding(vertical = 40.dp, horizontal = 40.dp),
            contentAlignment = Alignment.TopCenter,
        )
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = "로고",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(1.dp)
                            .width(142.5.dp)
                            .height(60.dp)
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Box(
                        modifier = modifier.fillMaxWidth()
                    ) {
                        // 로그인 박스 칸
                        Column(
                            modifier = modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            LoginTextField(
                                modifier = modifier,
                                text = idText,
                                onValueChanged = { idText.value = it },
                                placeholderText = "아이디를 입력하세요"
                            )
                            LoginTextField(
                                modifier = modifier,
                                text = pwText,
                                onValueChanged = { pwText.value = it },
                                placeholderText = "비밀번호를 입력하세요",
                                type = "password"
                            )
                            Text(
                                text = if(viewModel.isError.value){"아이디 또는 비밀번호가 올바르지 않습니다."}else{""},
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Main,
                                    textAlign = TextAlign.Right,
                                )
                            )
                        }
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(34.dp)
                ) {
                    LoginButton(
                        modifier = modifier,
                        text = "다음",
                        action = {
                            viewModel.setId(idText.value)
                            viewModel.setPassword(pwText.value)
                            viewModel.login()
//                            onMoveScreen(ScreenNavigate.HOME.name)
                        }
                    },
                    questionText = "계정이 없으신가요?",
                    questionActionText = "회원가입",
                    questionAction = { onMoveScreen(ScreenNavigate.SIGNUPINFOSTATUS.name) },
                )
            }
        }
    }
}