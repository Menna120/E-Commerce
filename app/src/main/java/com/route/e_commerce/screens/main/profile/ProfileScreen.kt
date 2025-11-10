package com.route.e_commerce.screens.main.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.route.e_commerce.ui.theme.ECommerceTheme
import com.route.e_commerce.utils.LocalMainNavController

@Composable
fun ProfileScreen() {
    LocalMainNavController.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) { Text(text = "Profile Screen") }
}

@Preview(showBackground = true)
@Composable
fun ProfileContentPreview() {
    ECommerceTheme {
        CompositionLocalProvider(LocalMainNavController provides rememberNavController()) {
            ProfileScreen()
        }
    }
}
