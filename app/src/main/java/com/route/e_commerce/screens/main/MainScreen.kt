package com.route.e_commerce.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.route.e_commerce.navigation.bottom_nav.BottomNavigationBar
import com.route.e_commerce.navigation.graph.MainNavGraph
import com.route.e_commerce.components.RouteLogo
import com.route.e_commerce.ui.theme.ECommerceTheme
import com.route.e_commerce.utils.LocalMainNavController

@Composable
fun MainScreen() {
    val nestedNavController = rememberNavController()

    CompositionLocalProvider(LocalMainNavController provides nestedNavController) {
        val scheme = MaterialTheme.colorScheme

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                RouteLogo(
                    modifier = Modifier
                        .padding(16.dp)
                        .height(24.dp),
                    tint = scheme.primary
                )
            },
            bottomBar = { BottomNavigationBar() }
        ) { innerPadding ->
            MainNavGraph(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    ECommerceTheme {
        MainScreen()
    }
}
