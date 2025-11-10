package com.route.e_commerce.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.route.e_commerce.navigation.Destination
import com.route.e_commerce.screens.auth.login.LoginScreen
import com.route.e_commerce.screens.auth.register.RegisterScreen

fun NavGraphBuilder.authNavGraph() {
    navigation<Destination.Auth>(startDestination = Destination.Login) {
        composable<Destination.Login> { LoginScreen() }
        composable<Destination.Register> { RegisterScreen() }
    }
}
