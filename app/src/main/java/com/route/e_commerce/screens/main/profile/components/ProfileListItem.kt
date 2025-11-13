package com.route.e_commerce.screens.main.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.route.e_commerce.R
import com.route.e_commerce.ui.theme.ECommerceTheme

@Composable
fun ProfileListItem(label: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val scheme = MaterialTheme.colorScheme
    val typo = MaterialTheme.typography

    ListItem(
        headlineContent = { Text(label, style = typo.bodyMedium) },
        modifier = modifier
            .clickable { onClick() }
            .fillMaxWidth(),
        trailingContent = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_forward),
                null
            )
        },
        colors = ListItemDefaults.colors(
            containerColor = scheme.background,
            headlineColor = scheme.onBackground,
            trailingIconColor = scheme.onBackground
        )
    )
}

@Preview
@Composable
private fun ProfileListItemPreview() {
    ECommerceTheme {
        ProfileListItem("Change Password") {}
    }
}
