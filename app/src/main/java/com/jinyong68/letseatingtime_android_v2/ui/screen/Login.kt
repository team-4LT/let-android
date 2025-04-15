package com.jinyong68.letseatingtime_android_v2.ui.screen

import com.jinyong68.letseatingtime_android_v2.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jinyong68.letseatingtime_android_v2.ui.compose.LoginTextField
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
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
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        )

                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.75f)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(White),
                    contentAlignment = Alignment.TopCenter,
                )
                    {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Image(
                            painter = painterResource(R.drawable.logo),
                            contentDescription = "로고",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .height(142.dp)
                        )
                        Box() {
                            Column {
                                LoginTextField(
                                    modifier = modifier,
                                    text = idText,
                                    placeholder = { Text("아이디를 입력해주세요") }
                                )
                                Spacer(modifier = Modifier.height(8.dp));
                                LoginTextField(
                                    modifier = modifier,
                                    text = pwText,
                                    placeholder = { Text("비밀번호를 입력해주세요") }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(120.dp))
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .width(344.dp)
                                .height(60.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            contentPadding = PaddingValues()
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colorStops = arrayOf(
                                                0.0f to Color(0xFFFF3939),
                                                1.0f to Color(0xFFFF8957)
                                            )
                                        ),
                                        shape = RoundedCornerShape(12.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("로그인", color = Color.White)
                            }
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