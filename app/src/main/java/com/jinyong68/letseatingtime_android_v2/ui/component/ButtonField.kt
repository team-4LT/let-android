package com.jinyong68.letseatingtime_android_v2.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jinyong68.letseatingtime_android_v2.ui.theme.Gray
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ButtonField(
    modifier: Modifier,
    buttonText:String,
    buttonAction : ()->Unit,
    questionText:String,
    questionActionText:String,
    questionAction: ()->Unit,
){
    var isButtonEnabled by remember { mutableStateOf(true) }
    var isQuestionEnabled by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        LoginButton(
            modifier = modifier.height(48.dp),
            text = buttonText,
            action = {
                if (isButtonEnabled) {
                    isButtonEnabled = false
                    buttonAction()
                    coroutineScope.launch {
                        delay(1000)
                        isButtonEnabled = true
                    }
                }
            }
        )

        Box {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(questionText)
                TextButton(
                    modifier = Modifier.alignByBaseline(),
                    enabled = isQuestionEnabled,
                    onClick = {
                        if (isQuestionEnabled) {
                            isQuestionEnabled = false
                            questionAction()
                            coroutineScope.launch {
                                delay(500)
                                isQuestionEnabled = true
                            }
                        }
                    },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(questionActionText, color = Main)
                }
            }
        }

        Text(
            "Copyright 2025. ALT All rights reserved.",
            color = Gray,
            fontWeight = FontWeight.Thin,
            fontSize = 12.sp
        )
    }
}