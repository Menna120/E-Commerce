package com.route.e_commerce.screens.auth.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.e_commerce.ui.theme.ECommerceTheme

@Composable
fun AuthButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val scheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    val shape = MaterialTheme.shapes

    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = shape.large,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = scheme.onPrimary,
            contentColor = scheme.primary,
            disabledContainerColor = scheme.secondary,
            disabledContentColor = scheme.background
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 12.dp),
            style = typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

@Preview
@Composable
fun AuthButtonPreview() {
    ECommerceTheme {
        AuthButton(onClick = {}, text = "Login")
    }
}

@Preview
@Composable
fun AuthButtonDisabledPreview() {
    ECommerceTheme {
        AuthButton(onClick = {}, text = "Login", enabled = false)
    }
}
