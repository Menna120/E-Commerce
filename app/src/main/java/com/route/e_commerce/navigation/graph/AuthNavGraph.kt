package com.route.e_commerce.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.route.e_commerce.navigation.Screen
import com.route.e_commerce.screens.auth.login.LoginScreen
import com.route.e_commerce.screens.auth.register.RegisterScreen

fun NavGraphBuilder.authNavGraph() {
    navigation<Screen.Auth>(startDestination = Screen.Login) {
        composable<Screen.Login> { LoginScreen() }
        composable<Screen.Register> { RegisterScreen() }
    }
}
