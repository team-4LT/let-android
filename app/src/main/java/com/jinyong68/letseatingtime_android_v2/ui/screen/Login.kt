package com.jinyong68.letseatingtime_android_v2.ui.screen
import com.jinyong68.letseatingtime_android_v2.R
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jinyong68.letseatingtime_android_v2.ui.compose.ButtonField
import com.jinyong68.letseatingtime_android_v2.ui.compose.TextField.LoginTextField
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.White


@Composable
fun Login(
    modifier : Modifier = Modifier,
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
                .align(Alignment.TopCenter)
        )

                Box( // 큰 흰색 박스
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.75f)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(White)
                        .padding(vertical = 40.dp)
                    ,
                    contentAlignment = Alignment.TopCenter,
                )
                {
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
                            Box { // 로그인 박스 칸
                                Column(
                                    horizontalAlignment = Alignment.End,
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    LoginTextField(
                                        modifier = modifier,
                                        text = idText,
                                        placeholder = { Text("아이디를 입력하세요") }
                                    )
                                    LoginTextField(
                                        modifier = modifier,
                                        text = pwText,
                                        placeholder = { Text("비밀번호를 입력하세요") }
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
                        ButtonField(modifier = modifier,
                            buttonText = "로그인",
                            buttonAction = {println("안녕하세요")},
                            questionText = "계정이 없으신가요?",
                            questionActionText = "회원가입",
                            questionAction = {println("안녕")}
                        )
                    }
                }
    }
    }

@Preview(showBackground = true)
@Composable
fun View(
    modifier: Modifier = Modifier
) {
    Box(

    ) {
        Login(modifier = modifier)
    }

}