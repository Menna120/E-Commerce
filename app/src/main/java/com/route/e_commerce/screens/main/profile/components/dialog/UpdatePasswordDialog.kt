package com.route.e_commerce.screens.main.profile.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.unit.dp
import com.route.e_commerce.R
import com.route.e_commerce.components.ECommerceTextField
import com.route.e_commerce.components.onBackgroundColors
import com.route.e_commerce.screens.main.profile.components.dialog.common.ECommerceDialog
import com.route.e_commerce.ui.theme.ECommerceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePasswordDialog(
    onConfirm: (current: String, new: String, re: String) -> Unit,
    onDismiss: () -> Unit
) {
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var rePassword by remember { mutableStateOf("") }

    ECommerceDialog(
        title = stringResource(R.string.update),
        onConfirm = { onConfirm(currentPassword, newPassword, rePassword) },
        onDismiss = onDismiss
    ) {
        UpdatePasswordContent(
            currentPassword = currentPassword,
            onCurrentPasswordChange = { currentPassword = it },
            newPassword = newPassword,
            onNewPasswordChange = { newPassword = it },
            rePassword = rePassword,
            onRePasswordChange = { rePassword = it }
        )
    }
}

@Composable
private fun UpdatePasswordContent(
    currentPassword: String,
    onCurrentPasswordChange: (String) -> Unit,
    newPassword: String,
    onNewPasswordChange: (String) -> Unit,
    rePassword: String,
    onRePasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scheme = MaterialTheme.colorScheme

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ECommerceTextField(
            modifier = Modifier.fillMaxWidth(),
            value = currentPassword,
            onValueChange = onCurrentPasswordChange,
            isPassword = true,
            label = stringResource(R.string.current_password),
            labelColor = scheme.onBackground,
            colors = OutlinedTextFieldDefaults.onBackgroundColors(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )
        )

        ECommerceTextField(
            modifier = Modifier.fillMaxWidth(),
            value = newPassword,
            onValueChange = onNewPasswordChange,
            isPassword = true,
            label = stringResource(R.string.new_password),
            labelColor = scheme.onBackground,
            colors = OutlinedTextFieldDefaults.onBackgroundColors(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )
        )

        ECommerceTextField(
            modifier = Modifier.fillMaxWidth(),
            value = rePassword,
            onValueChange = onRePasswordChange,
            isPassword = true,
            label = stringResource(R.string.re_enter_password),
            labelColor = scheme.onBackground,
            colors = OutlinedTextFieldDefaults.onBackgroundColors(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UpdatePasswordContentPreview() {
    ECommerceTheme {
        UpdatePasswordContent(
            currentPassword = "123456", onCurrentPasswordChange = {},
            newPassword = "1234567", onNewPasswordChange = {},
            rePassword = "1234567", onRePasswordChange = {}
        )
    }
}
