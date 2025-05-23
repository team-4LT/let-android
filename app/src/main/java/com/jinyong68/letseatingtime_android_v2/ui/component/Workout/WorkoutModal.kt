package com.jinyong68.letseatingtime_android_v2.ui.component.Workout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jinyong68.letseatingtime_android_v2.ui.theme.White
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import com.jinyong68.letseatingtime_android_v2.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.jinyong68.letseatingtime_android_v2.ui.theme.AppTypography
import com.jinyong68.letseatingtime_android_v2.ui.theme.Black
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun WorkoutModal(
    selectedLevel: String,
    onSelectLevel: (String) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = White,
            tonalElevation = 8.dp,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column (
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "난이도 설정",
                        style = AppTypography.headlineMedium,
                        color = Black
                    )
                    Image(
                        painter = painterResource(R.drawable.cancel),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable{onDismiss()}
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                val levels = listOf("초급", "중급", "고급")

                levels.forEach { level ->
                    val isSelected = selectedLevel == level
                    Button(
                        onClick = { onSelectLevel(level) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) Main else White,
                            contentColor = if (isSelected) White else Main
                        ),
                        border = BorderStroke(1.dp, Main),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {
                        Text(
                            text = level,
                            style = AppTypography.titleLarge,
                            fontWeight = FontWeight.Light)
                    }
                }
            }
        }
    }
}