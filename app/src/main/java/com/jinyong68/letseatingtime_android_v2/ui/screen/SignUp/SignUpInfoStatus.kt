package com.jinyong68.letseatingtime_android_v2.ui.screen.SignUp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.jinyong68.letseatingtime_android_v2.ui.component.TextField.LoginTextField
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.Placeholder
import com.jinyong68.letseatingtime_android_v2.ui.theme.White
import com.jinyong68.letseatingtime_android_v2.viewmodel.SignUpViewModel

@Composable
fun SignUpInfoStatus(
    modifier: Modifier = Modifier,
    onMoveScreen: (String) -> Unit,
    viewModel: SignUpViewModel
) { // 인적사항 작성하는 곳
    val nameText = rememberSaveable { mutableStateOf("") }
    val studentIdText = rememberSaveable { mutableStateOf("") }
    val schoolId = rememberSaveable { mutableStateOf("") }

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
                .align(Alignment.TopCenter)
        )

        Box(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(White)
                .padding(vertical = 40.dp),
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
                        Box {
                            Column(
                                horizontalAlignment = Alignment.End,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                LoginTextField(
                                    modifier = modifier,
                                    text = nameText,
                                    onValueChanged = { nameText.value = it },
                                    placeholder = { Text("아이디를 입력하세요", color = Placeholder) }
                                )
                                LoginTextField(
                                    modifier = modifier,
                                    text = studentIdText,
                                    onValueChanged = { studentIdText.value  = it },
                                    placeholder = { Text("비밀번호를 입력하세요.", color = Placeholder) }
                                )
                                LoginTextField(
                                    modifier = modifier,
                                    text = schoolId,
                                    onValueChanged = { schoolId.value = it },
                                    placeholder = { Text("비밀번호를 재입력하세요.", color = Placeholder) }
                                )
                                Text(
                                    text = "비밀번호가 올바르지 않습니다.",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(400),
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
                            onMoveScreen(ScreenNavigate.SIGNUPIDSTATUS.name);
                            viewModel.setName(nameText.toString());
                            viewModel.setStudent(studentIdText.toString());
                            viewModel.setSchoolId(schoolId.toString());
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
