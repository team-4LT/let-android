package com.jinyong68.letseatingtime_android_v2.ui.screen.SignUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jinyong68.letseatingtime_android_v2.ui.component.LoginButton
import com.jinyong68.letseatingtime_android_v2.ui.theme.Bg

@Composable
fun SignUpAllergyStatus(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .background(Bg)
            .statusBarsPadding()
    ) {
        Column {
            Text(
                text = "현재 가지고 있는 알러지를\n" +
                        "선택해 주세요."
            )
            LoginButton(modifier = Modifier, text = "회원가입", action = {})
        }
    }
}