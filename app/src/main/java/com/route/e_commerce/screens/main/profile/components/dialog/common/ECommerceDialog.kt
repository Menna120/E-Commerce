package com.route.e_commerce.screens.main.profile.components.dialog.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.route.e_commerce.ui.theme.ECommerceTheme

@Composable
fun ECommerceDialog(
    title: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    isConfirmEnabled: Boolean = true,
    content: @Composable (ColumnScope.() -> Unit)
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        EditProfileDialogContent(
            title = title,
            modifier = modifier,
            onConfirm = onConfirm,
            onDismiss = onDismiss,
            isConfirmEnabled = isConfirmEnabled,
            content = content
        )
    }
}

@Composable
fun EditProfileDialogContent(
    title: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    isConfirmEnabled: Boolean = true,
    content: @Composable (ColumnScope.() -> Unit)
) {
    val scheme = MaterialTheme.colorScheme

    OutlinedCard(
        modifier = modifier.padding(16.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = scheme.background,
            contentColor = scheme.onBackground
        ),
        border = BorderStroke(1.dp, scheme.primary.copy(.3f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = scheme.onBackground,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            content()

            DialogButtons(
                modifier = Modifier.align(Alignment.End),
                isConfirmEnabled = isConfirmEnabled,
                onSaveClick = onConfirm,
                onCancelClick = onDismiss
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileDialogPreview() {
    ECommerceTheme {
        EditProfileDialogContent(title = "Edit", onDismiss = {}, onConfirm = {}) {}
    }
}
