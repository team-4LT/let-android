package com.jinyong68.letseatingtime_android_v2.ui.component.TextField

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jinyong68.letseatingtime_android_v2.ui.theme.Gray
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.Placeholder
import com.jinyong68.letseatingtime_android_v2.ui.theme.pretendard

@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    text: MutableState<String>,
    onValueChanged: (String) -> Unit,
    placeholderText: String = "",
    type: String? = null,
    keyboardType: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text.value,
        onValueChange = onValueChanged,
        modifier = modifier
            .fillMaxWidth()
            .height(68.dp),
        placeholder = {
            Text(
                text = placeholderText,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp,
                    color = Placeholder
                )
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontFamily = pretendard,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Normal,
            lineHeight = 20.sp
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Main,
            unfocusedBorderColor = Gray
        ),
        keyboardOptions = keyboardType,
        visualTransformation = if(type == "password" && !passwordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = {
            if(type == "password") {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if(passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            }
        }
    )
}
