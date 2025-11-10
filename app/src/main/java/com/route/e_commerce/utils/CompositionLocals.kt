package com.route.e_commerce.utils

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

val LocalMainNavController =
    staticCompositionLocalOf<NavHostController> { error("No NavController found!") }

val LocalAppNavController =
    staticCompositionLocalOf<NavHostController> { error("No NavController found!") }
