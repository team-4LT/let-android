package com.jinyong68.letseatingtime_android_v2.ui.component.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.jinyong68.letseatingtime_android_v2.R
import androidx.compose.ui.unit.dp
import com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.ui.theme.White

@Composable
fun WarningTable(
    meal : String,
    time : String,
    allergy : String
){
    Row (
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(White, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clip(RoundedCornerShape(8.dp)),
    ){
        Image(
            painter = painterResource(R.drawable.warning),
            contentDescription = null)
        Text(
            text = "${time}에 ${allergy}가 포함되어있는 ${meal}가 나옵니다.",
            color = Main,
            style = AppTypography.titleLarge,
            fontWeight = FontWeight.Light
        )
    }
}