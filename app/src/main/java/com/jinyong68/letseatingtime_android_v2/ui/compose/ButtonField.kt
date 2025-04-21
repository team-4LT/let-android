package com.jinyong68.letseatingtime_android_v2.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jinyong68.letseatingtime_android_v2.ScreenNavigate
import com.jinyong68.letseatingtime_android_v2.ui.screen.SignUp.SignUpIdStatus
import com.jinyong68.letseatingtime_android_v2.ui.theme.Grey
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main

@Composable
fun ButtonField(
    modifier: Modifier,
    buttonText:String,
    buttonAction : ()->Unit,
    questionText:String,
    questionActionText:String,
    questionAction: ()->Unit,
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginButton(
            modifier = modifier,
            text = buttonText,
            action = {
                buttonAction()
            }
        )
        Box {
            Row(verticalAlignment = Alignment.CenterVertically)
            {
                Text(questionText + " ")
                TextButton(
                    modifier = Modifier.alignByBaseline(),
                    onClick = {
                        questionAction()
                    },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(questionActionText, color = Main)
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