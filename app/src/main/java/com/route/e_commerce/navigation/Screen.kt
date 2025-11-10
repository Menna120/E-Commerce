package com.route.e_commerce.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable
    data object Login : Screen

    @Serializable
    data object Register : Screen

    @Serializable
    data object Home : Screen

    @Serializable
    data object Category : Screen

    @Serializable
    data object Wishlist : Screen

    @Serializable
    data object Profile : Screen

    @Serializable
    data class ProductList(val subCategoryId: String) : Screen

    @Serializable
    data class ProductDetails(val productId: String) : Screen

    @Serializable
    data object Cart : Screen

    @Serializable
    data object Search : Screen

    @Serializable
    data object Main : Screen

    @Serializable
    data object Auth : Screen
}
