package com.jinyong68.letseatingtime_android_v2.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jinyong68.letseatingtime_android_v2.ScreenNavigate
import com.jinyong68.letseatingtime_android_v2.ui.theme.Bg
import com.jinyong68.letseatingtime_android_v2.ui.theme.BtmNavIcon
import com.jinyong68.letseatingtime_android_v2.ui.theme.Main
import com.jinyong68.letseatingtime_android_v2.R
import com.jinyong68.letseatingtime_android_v2.ui.theme.Gray
import com.jinyong68.letseatingtime_android_v2.ui.theme.White

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ScreenNavigate.SPLASH.name

    val items = listOf(
        BottomNavItem( ScreenNavigate.WORKOUT.name, R.drawable.exercise),
        BottomNavItem(ScreenNavigate.HOME.name, R.drawable.home),
        BottomNavItem(ScreenNavigate.PROFILE.name, R.drawable.profile)
    )
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .navigationBarsPadding().drawBehind {
                drawLine(
                    color = Gray,
                    start = androidx.compose.ui.geometry.Offset(0f, 0f),
                    end = androidx.compose.ui.geometry.Offset(size.width, 0f),
                    strokeWidth = 1.dp.toPx()
                )
            },
        windowInsets = WindowInsets.systemBars.only(WindowInsetsSides.Bottom),
        containerColor = White,
    ) {
        items.forEach { destination ->
            val isSelected = destination.route == currentRoute
            val animatedColor by animateColorAsState(
                animationSpec = tween(
                    durationMillis = 500,
                ),
                targetValue = if (isSelected) Main else BtmNavIcon
            )
            NavigationBarItem(
                icon = { Image(
                    painter = painterResource(destination.icon),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        animatedColor
                    ),
                    modifier = Modifier
                        .size(24.dp)
                ) },
                selected = isSelected,
                onClick = {
                    navController.navigate(destination.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

data class BottomNavItem(
    val route: String,
    val icon: Int
)