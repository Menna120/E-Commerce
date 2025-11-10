package com.route.e_commerce.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.route.e_commerce.navigation.Destination
import com.route.e_commerce.screens.cart.CartScreen
import com.route.e_commerce.screens.main.MainScreen
import com.route.e_commerce.screens.product_details.ProductDetailsScreen
import com.route.e_commerce.screens.search.SearchScreen
import com.route.e_commerce.utils.LocalAppNavController

@Composable
fun AppNavGraph(startDestination: Destination) {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalAppNavController provides navController) {
        NavHost(navController = navController, startDestination = startDestination) {
            authNavGraph()
            composable<Destination.Main> { MainScreen() }
            composable<Destination.ProductDetails> { backStackEntry ->
                val productDetails = backStackEntry.toRoute<Destination.ProductDetails>()
                ProductDetailsScreen(productId = productDetails.productId)
            }
            composable<Destination.Cart> { CartScreen() }
            composable<Destination.Search> { SearchScreen() }
        }
    }
}
