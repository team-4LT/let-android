package com.jinyong68.letseatingtime_android_v2.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

@Composable
fun SignUp(modifier : Modifier = Modifier,){
    val nameText: MutableState<String> = rememberSaveable {
        mutableStateOf(
            ""
        )
    }
    val numberText: MutableState<Int?> = rememberSaveable {
        mutableStateOf(null);
    }
    val schoolText: MutableState<Int?> = rememberSaveable {
        mutableStateOf(null);
    }
}