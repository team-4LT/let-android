package com.jinyong68.letseatingtime_android_v2.ui.screen.SignUp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.jinyong68.letseatingtime_android_v2.ScreenNavigate
import com.jinyong68.letseatingtime_android_v2.constant.AllergyList
import com.jinyong68.letseatingtime_android_v2.ui.component.LoginButton
import com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography
import com.jinyong68.letseatingtime_android_v2.ui.theme.Black
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.Placeholder
import com.jinyong68.letseatingtime_android_v2.ui.theme.White
import com.jinyong68.letseatingtime_android_v2.viewmodel.SignUpViewModel

@Composable
fun SignUpAllergyStatus(
    modifier: Modifier = Modifier,
    onMoveScreen: (String) -> Unit,
    viewModel: SignUpViewModel
){
    LaunchedEffect(Unit) {
        viewModel.clearAllergy()
    }

    LaunchedEffect(viewModel.isSuccess.value) {
        if (viewModel.isSuccess.value) {
            onMoveScreen(ScreenNavigate.LOGIN.name)
            viewModel.isSuccess.value = false
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(White)
    ) {
        if (viewModel.isSuccess.value) {
            AlertDialog(
                onDismissRequest = { viewModel.isSuccess.value = false },
                confirmButton = {
                    Text(
                        text = "확인",
                        modifier = Modifier.clickable {
                            viewModel.isSuccess.value = false
                            onMoveScreen(ScreenNavigate.LOGIN.name)
                        }
                    )
                },
                title = { Text("회원가입 완료") },
                text = { Text("회원가입이 성공적으로 완료되었습니다.") }
            )
        }

        Icon(
            imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription = "뒤로가기",
            tint = Black,
            modifier = Modifier
                .offset(x = 16.dp, y = 40.dp)
                .size(30.dp)
                .align(Alignment.TopStart)
                .zIndex(99f)
                .clickable {
                    onMoveScreen(ScreenNavigate.SIGNUPINFOSTATUS.name)
                }
        )
        Column (
            modifier.fillMaxSize()
                .padding(horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            Text(
                text = "현재 가지고 있는 알러지를\n" +
                        "선택해 주세요.",
                style = AppTypography.displayMedium,
                color = Black,
                textAlign = TextAlign.Center
            )
            FlowRow (
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                AllergyList.forEach { item ->
                    val selected = viewModel.allergyList.value.contains(item)
                    Row(
                        modifier = Modifier
                            .border(1.dp, if (selected) White else Placeholder, RoundedCornerShape(4.dp))
                            .clickable { viewModel.changeAllergy(item) }
                            .background(if (selected) Main else White, RoundedCornerShape(4.dp))
                    ) {
                        Text(
                            item,
                            Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            color = if (selected) White else Placeholder
                        )
                    }
                }
            }
            if (viewModel.isError.value) {
                Text(
                    text = viewModel.errorMessage.value,
                    color = Main,
                    style = AppTypography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            LoginButton(modifier = modifier, text = "회원가입", action = {
               viewModel.signUp()
            })
        }
    }
}