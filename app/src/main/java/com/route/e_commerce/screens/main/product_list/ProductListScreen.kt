package com.route.e_commerce.screens.main.product_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.route.e_commerce.navigation.Destination
import com.route.e_commerce.ui.theme.ECommerceTheme
import com.route.e_commerce.utils.LocalAppNavController

@Composable
fun ProductListScreen(subCategoryId: String) {
    val navController = LocalAppNavController.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "sub-category: $subCategoryId")

        Button(onClick = { navController.navigate(Destination.ProductDetails("Jeans")) }) {
            Text(text = "Go to Product Details Screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListScreenPreview() {
    ECommerceTheme {
        CompositionLocalProvider(LocalAppNavController provides rememberNavController()) {
            ProductListScreen(subCategoryId = "123")
        }
    }
}
