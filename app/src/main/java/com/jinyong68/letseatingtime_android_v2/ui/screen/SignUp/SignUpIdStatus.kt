package com.jinyong68.letseatingtime_android_v2.ui.screen.SignUp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
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
import androidx.compose.ui.zIndex
import com.jinyong68.letseatingtime_android_v2.R
import com.jinyong68.letseatingtime_android_v2.ScreenNavigate
import com.jinyong68.letseatingtime_android_v2.ui.component.ButtonField
import com.jinyong68.letseatingtime_android_v2.ui.component.TextField.LoginTextField
import com.jinyong68.letseatingtime_android_v2.ui.theme.Black
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.White
import com.jinyong68.letseatingtime_android_v2.viewmodel.SignUpViewModel

@Composable
fun SignUpIdStatus(
    modifier: Modifier = Modifier,
    onMoveScreen: (String) -> Unit,
    viewModel: SignUpViewModel
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
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
        Icon(
            imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription = "뒤로가기",
            tint = White,
            modifier = Modifier
                .offset(x = 16.dp, y = 40.dp)
                .size(30.dp)
                .align(Alignment.TopStart)
                .zIndex(99f)
                .clickable {
                    onMoveScreen(ScreenNavigate.SIGNUPINFOSTATUS.name)
                }
        )
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.77f)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(White)
                .padding(vertical = 40.dp, horizontal = 40.dp),
            contentAlignment = Alignment.TopCenter,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
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
                        Box {
                            Column(
                                horizontalAlignment = Alignment.End,
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                LoginTextField(
                                    modifier = modifier,
                                    text = viewModel.id,
                                    onValueChanged = { viewModel.id.value = it },
                                    placeholderText = "아이디를 입력하세요"
                                )
                                LoginTextField(
                                    modifier = modifier,
                                    text = viewModel.password,
                                    onValueChanged = { viewModel.password.value  = it },
                                    placeholderText = "비밀번호를 입력하세요.",
                                    type = "password"
                                )
                                LoginTextField(
                                    modifier = modifier,
                                    text = viewModel.checkPassword,
                                    onValueChanged = { viewModel.checkPassword.value = it },
                                    placeholderText = "비밀번호를 재입력하세요.",
                                    type = "password"
                                )
                                Text(
                                    text = if (viewModel.isError.value) viewModel.errorMessage.value else "",
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
                    ButtonField(
                        modifier = modifier,
                        buttonText = "다음",
                        buttonAction = {
                            if (viewModel.validateSignUpId()) {
                                println("봐라")
                                println(viewModel.number.value)
                            onMoveScreen(ScreenNavigate.SIGNUPALLERGYSTATUS.name)
                        }
                            Log.d("value", "${viewModel.logValues()}"); },
                        questionText = "이미 계정이 있으신가요?",
                        questionActionText = "로그인",
                        questionAction = { onMoveScreen(ScreenNavigate.LOGIN.name) },
                    )
                }
            }
        }
    }
}