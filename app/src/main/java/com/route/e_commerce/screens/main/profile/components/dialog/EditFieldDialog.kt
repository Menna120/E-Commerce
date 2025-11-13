package com.route.e_commerce.screens.main.profile.components.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.route.e_commerce.R
import com.route.e_commerce.components.ECommerceTextField
import com.route.e_commerce.components.onBackgroundColors
import com.route.e_commerce.screens.main.profile.EditableField
import com.route.e_commerce.screens.main.profile.components.dialog.common.ECommerceDialog
import com.route.e_commerce.ui.theme.ECommerceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditFieldDialog(
    label: String,
    value: String,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var newValue by remember { mutableStateOf(value) }

    ECommerceDialog(
        title = stringResource(R.string.edit, ""),
        onConfirm = { onConfirm(newValue) },
        onDismiss = onDismiss
    ) {
        EditFieldDialogContent(
            label = label,
            value = newValue,
            onValueChange = { newValue = it }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditFieldDialogContent(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    val scheme = MaterialTheme.colorScheme

    ECommerceTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = label,
        labelColor = scheme.onBackground,
        colors = OutlinedTextFieldDefaults.onBackgroundColors(),
        keyboardOptions = KeyboardOptions(
            keyboardType = when (label) {
                stringResource(EditableField.PHONE.labelRes) -> KeyboardType.Phone
                stringResource(EditableField.EMAIL.labelRes) -> KeyboardType.Email
                else -> KeyboardType.Text
            },
            imeAction = ImeAction.Done
        )
    )

}

@Preview
@Composable
fun EditFieldDialogContentPreview() {
    ECommerceTheme {
        Surface {
            EditFieldDialogContent(
                label = "Field",
                value = "Value",
                onValueChange = {}
            )
        }
    }
}
