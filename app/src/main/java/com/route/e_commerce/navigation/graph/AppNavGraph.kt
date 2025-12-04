package com.route.e_commerce.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.route.e_commerce.navigation.Screen
import com.route.e_commerce.screens.cart.CartScreen
import com.route.e_commerce.screens.main.MainScreen
import com.route.e_commerce.screens.search.SearchScreen
import com.route.e_commerce.utils.LocalAppNavController

@Composable
fun AppNavGraph(startScreen: Screen) {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalAppNavController provides navController) {
        NavHost(navController = navController, startDestination = startScreen) {
            authNavGraph()
            composable<Screen.Main> { MainScreen() }
            composable<Screen.Cart> { CartScreen() }
            composable<Screen.Search> { SearchScreen() }
        }
    }
}
