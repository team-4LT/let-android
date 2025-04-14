package com.jinyong68.letseatingtime_android_v2.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jinyong68.letseatingtime_android_v2.ui.theme.Black
import com.jinyong68.letseatingtime_android_v2.ui.theme.Grey
import com.jinyong68.letseatingtime_android_v2.ui.theme.White


@Composable
fun Login(
    modifier : Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        OutlinedTextField(
            modifier = Modifier
                .width(344.dp)
                .height(70.dp),
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text(
                    fontSize = 16.sp,
                    text = "아이디을 입력하세요",
                    modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.CenterStart)
                )
            },
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                lineHeight = 70.sp // ✅ 텍스트 입력 위치를 placeholder에 맞춤
            ),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Black,
                unfocusedBorderColor = Grey,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun View(
    modifier: Modifier = Modifier
) {
    Login(modifier = modifier)
}