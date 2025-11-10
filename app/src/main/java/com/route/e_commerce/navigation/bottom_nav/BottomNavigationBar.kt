package com.route.e_commerce.navigation.bottom_nav

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.route.e_commerce.ui.theme.ECommerceTheme
import com.route.e_commerce.utils.LocalMainNavController

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier) {
    val scheme = MaterialTheme.colorScheme
    val navController = LocalMainNavController.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        modifier = modifier.clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
        containerColor = scheme.primary
    ) {
        BottomNavItem.entries.forEach { item ->
            val isSelected =
                currentDestination?.hierarchy?.any { it.hasRoute(item.screen::class) } == true

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.screen) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    val backgroundColor by animateColorAsState(
                        targetValue = if (isSelected) scheme.onPrimary else scheme.primary,
                        animationSpec = tween(500),
                        label = "background color"
                    )

                    Icon(
                        imageVector = ImageVector.vectorResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(backgroundColor)
                            .padding(8.dp)
                    )
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = scheme.primary,
                    unselectedIconColor = scheme.onPrimary,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    ECommerceTheme {
        CompositionLocalProvider(LocalMainNavController provides rememberNavController()) {
            BottomNavigationBar()
        }
    }
}
