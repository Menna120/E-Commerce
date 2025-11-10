package com.route.e_commerce.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.route.e_commerce.navigation.Screen
import com.route.e_commerce.screens.main.category.CategoryScreen
import com.route.e_commerce.screens.main.home.HomeScreen
import com.route.e_commerce.screens.main.product_list.ProductListScreen
import com.route.e_commerce.screens.main.profile.ProfileScreen
import com.route.e_commerce.screens.main.wishlist.WishlistScreen
import com.route.e_commerce.utils.LocalMainNavController

@Composable
fun MainNavGraph(modifier: Modifier) {
    val navController = LocalMainNavController.current

    NavHost(
        navController = navController,
        startDestination = Screen.Home,
        modifier = modifier
    ) {
        composable<Screen.Home> { HomeScreen() }
        composable<Screen.Category> { CategoryScreen() }
        composable<Screen.Wishlist> { WishlistScreen() }
        composable<Screen.Profile> { ProfileScreen() }
        composable<Screen.ProductList> { backStackEntry ->
            val productList = backStackEntry.toRoute<Screen.ProductList>()
            ProductListScreen(subCategoryId = productList.subCategoryId)
        }
    }
}
