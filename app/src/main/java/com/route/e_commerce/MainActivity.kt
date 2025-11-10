package com.route.e_commerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.route.e_commerce.navigation.graph.AppNavGraph
import com.route.e_commerce.ui.theme.ECommerceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen().setKeepOnScreenCondition {
            viewModel.startDestination.value == null
        }
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        setContent {
            ECommerceTheme {
                val startDestination by viewModel.startDestination.collectAsState()

                startDestination?.let {
                    AppNavGraph(startDestination = it)
                }
            }
        }
    }
}
