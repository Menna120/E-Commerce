package com.route.e_commerce.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.route.e_commerce.components.RouteLogo
import com.route.e_commerce.navigation.Screen
import com.route.e_commerce.navigation.bottom_nav.BottomNavigationBar
import com.route.e_commerce.navigation.graph.MainNavGraph
import com.route.e_commerce.ui.theme.ECommerceTheme
import com.route.e_commerce.utils.CustomTopBar
import com.route.e_commerce.utils.LocalMainNavController
import androidx.navigation.NavDestination.Companion.hasRoute

@Composable
fun MainScreen() {
    val nestedNavController = rememberNavController()

    CompositionLocalProvider(LocalMainNavController provides nestedNavController) {
        val scheme = MaterialTheme.colorScheme
        var searchQuery by remember { mutableStateOf("") }
        val navBackStackEntry by nestedNavController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val showTopBar = currentDestination?.hasRoute<Screen.Home>() == true ||
                currentDestination?.hasRoute<Screen.Category>() == true ||
                currentDestination?.hasRoute<Screen.Wishlist>() == true ||
                currentDestination?.hasRoute<Screen.ProductList>() == true

        val showBottomBar = currentDestination?.hasRoute<Screen.Home>() == true ||
                currentDestination?.hasRoute<Screen.Category>() == true ||
                currentDestination?.hasRoute<Screen.Wishlist>() == true ||
                currentDestination?.hasRoute<Screen.Profile>() == true ||
                currentDestination?.hasRoute<Screen.ProductList>() == true

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                if (showTopBar) {
                    Column {
                        RouteLogo(
                            modifier = Modifier
                                .statusBarsPadding()
                                .padding(start = 16.dp, top = 8.dp)
                                .height(24.dp),
                            tint = scheme.primary
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        CustomTopBar(
                            onSearch = { query ->
                                searchQuery = query
                            }
                        )
                    }
                }
            },
            bottomBar = { if(showBottomBar){BottomNavigationBar() }}
        ) { innerPadding ->
            MainNavGraph(
                modifier = Modifier.padding(innerPadding),
                searchQuery = searchQuery
            )
        }
    }
}
@Preview
@Composable
fun MainScreenPreview() {
    ECommerceTheme {
        MainScreen()
    }
}
