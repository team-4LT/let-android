package com.jinyong68.letseatingtime_android_v2.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jinyong68.letseatingtime_android_v2.ui.theme.Black
import com.jinyong68.letseatingtime_android_v2.ui.theme.Grey
import com.jinyong68.letseatingtime_android_v2.ui.theme.White

@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: @Composable (() -> Unit)?,
    keyboardType: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    onValueChange: (String) -> Unit
    ){
    OutlinedTextField(
        modifier = modifier
            .width(344.dp)
            .height(70.dp)
            .background(
                White
            )
        ,
        value = text,
        onValueChange = onValueChange,
        placeholder = placeholder,
        shape = RoundedCornerShape(12.dp),
        textStyle = TextStyle(
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            lineHeight = 70.sp // 텍스트 입력 위치를 placeholder에 맞춤
        ),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Black,
            unfocusedBorderColor = Grey,
        ),
        keyboardOptions = keyboardType
    )
}