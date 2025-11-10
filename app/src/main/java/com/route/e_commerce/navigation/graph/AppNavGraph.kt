package com.route.e_commerce.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.route.e_commerce.navigation.Screen
import com.route.e_commerce.screens.cart.CartScreen
import com.route.e_commerce.screens.main.MainScreen
import com.route.e_commerce.screens.product_details.ProductDetailsScreen
import com.route.e_commerce.screens.search.SearchScreen
import com.route.e_commerce.utils.LocalAppNavController

@Composable
fun AppNavGraph(startScreen: Screen) {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalAppNavController provides navController) {
        NavHost(navController = navController, startDestination = startScreen) {
            authNavGraph()
            composable<Screen.Main> { MainScreen() }
            composable<Screen.ProductDetails> { backStackEntry ->
                val productDetails = backStackEntry.toRoute<Screen.ProductDetails>()
                ProductDetailsScreen(productId = productDetails.productId)
            }
            composable<Screen.Cart> { CartScreen() }
            composable<Screen.Search> { SearchScreen() }
        }
    }
}
