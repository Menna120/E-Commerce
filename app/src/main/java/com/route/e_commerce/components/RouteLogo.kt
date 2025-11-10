package com.route.e_commerce.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.route.e_commerce.R
import com.route.e_commerce.ui.theme.ECommerceTheme

@Composable
fun RouteLogo(
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onPrimary
) {
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_route_logo),
        contentDescription = null,
        modifier = modifier,
        tint = tint
    )
}

@Preview
@Composable
private fun RouteLogoPreview() {
    ECommerceTheme {
        RouteLogo()
    }
}
