package com.route.e_commerce.navigation.bottom_nav

import androidx.annotation.DrawableRes
import com.route.e_commerce.R
import com.route.e_commerce.navigation.Destination

enum class BottomNavItem(
    val destination: Destination,
    val title: String,
    @param:DrawableRes val icon: Int
) {
    Home(
        destination = Destination.Home,
        title = "Home",
        icon = R.drawable.ic_home
    ),

    Category(
        destination = Destination.Category,
        title = "Category",
        icon = R.drawable.ic_category
    ),

    Wishlist(
        destination = Destination.Wishlist,
        title = "Wishlist",
        icon = R.drawable.ic_heart
    ),

    Profile(
        destination = Destination.Profile,
        title = "Profile",
        icon = R.drawable.ic_user
    )
}
