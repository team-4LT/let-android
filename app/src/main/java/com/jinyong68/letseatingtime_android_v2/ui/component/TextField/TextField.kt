package com.jinyong68.letseatingtime_android_v2.ui.component.TextField

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.jinyong68.letseatingtime_android_v2.ui.theme.Gray
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.White

@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    text: MutableState<String>,
    onValueChanged: (String) -> Unit,
    placeholder: @Composable (() -> Unit)?,
    keyboardType: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
) {
    OutlinedTextField(
        modifier = modifier
            .width(344.dp)
            .height(70.dp)
            .padding(start = 13.dp)
            .background(White),
        value = text.value,
        onValueChange = onValueChanged,
        placeholder = placeholder,
        shape = RoundedCornerShape(12.dp),
        textStyle = TextStyle(
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            lineHeight = 20.sp
        ),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Main,
            unfocusedBorderColor = Gray
        ),
        keyboardOptions = keyboardType
    )
}
