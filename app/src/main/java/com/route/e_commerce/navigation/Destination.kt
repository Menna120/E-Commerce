package com.route.e_commerce.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Destination {
    @Serializable
    data object Login : Destination

    @Serializable
    data object Register : Destination

    @Serializable
    data object Home : Destination

    @Serializable
    data object Category : Destination

    @Serializable
    data object Wishlist : Destination

    @Serializable
    data object Profile : Destination

    @Serializable
    data class ProductList(val subCategoryId: String) : Destination

    @Serializable
    data class ProductDetails(val productId: String) : Destination

    @Serializable
    data object Cart : Destination

    @Serializable
    data object Search : Destination

    @Serializable
    data object Main : Destination

    @Serializable
    data object Auth : Destination
}
