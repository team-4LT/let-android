package com.jinyong68.letseatingtime_android_v2.ui.screen

import androidx.compose.foundation.BorderStroke
import com.jinyong68.letseatingtime_android_v2.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
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
import com.jinyong68.letseatingtime_android_v2.ui.compose.LoginTextField
import com.jinyong68.letseatingtime_android_v2.ui.theme.Grey
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main2
import com.jinyong68.letseatingtime_android_v2.ui.theme.White


@Composable
fun Login(
    modifier : Modifier = Modifier,
) {
    val idText: MutableState<String> = rememberSaveable {
        mutableStateOf(
            ""
        )
    }
    val pwText: MutableState<String> = rememberSaveable {
        mutableStateOf(
            ""
        )
    }

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
                                        placeholder = { Text("아이디를 입력해주세요") }
                                    )
                                    LoginTextField(
                                        modifier = modifier,
                                        text = pwText,
                                        placeholder = { Text("비밀번호를 입력해주세요") }
                                    )
                                    Spacer(modifier = Modifier.height(5.dp));
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
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box( // 로그인 박스
                                modifier = Modifier
                                    .clickable {}
                                    .width(344.dp)
                                    .height(58.dp)
                                    .padding(start = 13.dp, top = 0.dp, end = 13.dp, bottom = 0.dp)
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colorStops = arrayOf(
                                                0.0f to Main,
                                                1.0f to Main2
                                            )
                                        ),
                                        shape = RoundedCornerShape(12.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "로그인",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight(500),
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )

                                )
                            }
                            Box { // 회원가입
                                Row(verticalAlignment = Alignment.CenterVertically)
                                {
                                    Text("계정이 없으신가요? ")
                                    TextButton(
                                        modifier = Modifier.alignByBaseline(),
                                        onClick = {
                                            println("안녕하세여")
                                        },
                                        contentPadding = PaddingValues(0.dp)
                                    ) {
                                        Text("회원가입", color = Main)
                                    }
                                }
                            }
                            Text(
                                "Copyright 2025. ALT All rights reserved.",
                                color = Grey,
                                fontWeight = FontWeight.Thin,
                                fontSize = 12.sp
                            )
                        }
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