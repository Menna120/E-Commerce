package com.route.e_commerce.screens.main.profile.components.dialog.common

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.route.e_commerce.R
import com.route.e_commerce.ui.theme.ECommerceTheme

@Composable
fun DialogButtons(
    modifier: Modifier = Modifier,
    isConfirmEnabled: Boolean = true,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    val scheme = MaterialTheme.colorScheme
    val typo = MaterialTheme.typography

    Row(modifier = modifier) {

        TextButton(onClick = onCancelClick) {
            Text(
                text = stringResource(R.string.cancel),
                color = scheme.secondary,
                style = typo.bodySmall
            )
        }

        TextButton(onClick = onSaveClick, enabled = isConfirmEnabled) {
            Text(
                text = stringResource(R.string.save),
                color = scheme.onBackground,
                style = typo.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DialogButtonsPreview() {
    ECommerceTheme {
        DialogButtons(onSaveClick = {}, onCancelClick = {})
    }
}
