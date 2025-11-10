package com.route.e_commerce.navigation.bottom_nav

import androidx.annotation.DrawableRes
import com.route.e_commerce.R
import com.route.e_commerce.navigation.Screen

enum class BottomNavItem(
    val screen: Screen,
    val title: String,
    @param:DrawableRes val icon: Int
) {
    Home(
        screen = Screen.Home,
        title = "Home",
        icon = R.drawable.ic_home
    ),

    Category(
        screen = Screen.Category,
        title = "Category",
        icon = R.drawable.ic_category
    ),

    Wishlist(
        screen = Screen.Wishlist,
        title = "Wishlist",
        icon = R.drawable.ic_heart
    ),

    Profile(
        screen = Screen.Profile,
        title = "Profile",
        icon = R.drawable.ic_user
    )
}
