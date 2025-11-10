package com.route.e_commerce.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.e_commerce.R
import com.route.e_commerce.ui.theme.ECommerceTheme

@Composable
fun ECommerceTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholderText: String? = null,
    isPassword: Boolean = false,
    readOnly: Boolean = false,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    labelColor: Color = MaterialTheme.colorScheme.onPrimary,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val typography = MaterialTheme.typography
    val shape = MaterialTheme.shapes

    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            color = labelColor,
            style = typography.bodyMedium
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = typography.bodyMedium,
            readOnly = readOnly,
            placeholder = {
                Text(
                    text = placeholderText ?: label,
                    style = typography.bodyMedium.copy(fontWeight = FontWeight.Light)
                )
            },
            supportingText = {
                errorMessage?.let {
                    Text(
                        text = it,
                        style = typography.bodyMedium.copy(fontWeight = FontWeight.Light)
                    )
                }
            },
            trailingIcon = {
                if (isPassword) {
                    IconButton({ passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                if (passwordVisible) R.drawable.ic_view
                                else R.drawable.ic_hide
                            ),
                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
                        )
                    }
                } else {
                    trailingIcon?.invoke()
                }
            },
            isError = errorMessage != null,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            shape = shape.medium,
            colors = colors,
        )
    }
}

@Composable
fun OutlinedTextFieldDefaults.authColors(): TextFieldColors {
    val scheme = MaterialTheme.colorScheme

    return colors(
        focusedBorderColor = scheme.onPrimary,
        unfocusedBorderColor = scheme.onPrimary,
        focusedContainerColor = scheme.onPrimary,
        unfocusedContainerColor = scheme.onPrimary,
        focusedTextColor = scheme.tertiary,
        unfocusedTextColor = scheme.tertiary.copy(.7f),
        cursorColor = scheme.tertiary,
        focusedPlaceholderColor = scheme.tertiary.copy(.7f),
        unfocusedPlaceholderColor = scheme.tertiary.copy(.7f),
        focusedTrailingIconColor = scheme.secondary,
        unfocusedTrailingIconColor = scheme.outline
    )
}

@Composable
fun OutlinedTextFieldDefaults.accountColors(): TextFieldColors {
    val scheme = MaterialTheme.colorScheme

    return colors(
        focusedBorderColor = scheme.secondary,
        unfocusedBorderColor = scheme.secondary,
        focusedTextColor = scheme.onBackground,
        unfocusedTextColor = scheme.onBackground,
        focusedTrailingIconColor = scheme.onBackground,
        unfocusedTrailingIconColor = scheme.onBackground
    )
}

@Preview
@Composable
fun PasswordTextFieldPreview() {
    ECommerceTheme {
        ECommerceTextField(
            value = "example@email.com",
            onValueChange = {},
            label = "Email",
            isPassword = true,
            colors = OutlinedTextFieldDefaults.authColors()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AccountTextFieldPreview() {
    ECommerceTheme {
        ECommerceTextField(
            value = "mohamed.N@gmail.com",
            onValueChange = {},
            label = "Your E-mail",
            readOnly = true,
            labelColor = MaterialTheme.colorScheme.onBackground,
            colors = OutlinedTextFieldDefaults.accountColors()
        ) {
            Icon(painterResource(R.drawable.ic_edit), "")
        }
    }
}
