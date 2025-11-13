package com.route.e_commerce.screens.main.profile.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun AddAddressDialog(
    onAddAddress: (name: String, details: String, phone: String, city: String) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var details by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    val isFormValid by remember(name, details, phone, city) {
        mutableStateOf(name.isNotBlank() && details.isNotBlank() && phone.isNotBlank() && city.isNotBlank())
    }

    ECommerceDialog(
        title = stringResource(R.string.add_new_address),
        isConfirmEnabled = isFormValid,
        onConfirm = { onAddAddress(name, details, phone, city) },
        onDismiss = onDismiss
    ) {
        AddAddressContent(
            name = name,
            onNameChange = { name = it },
            details = details,
            onDetailsChange = { details = it },
            phone = phone,
            onPhoneChange = { phone = it },
            city = city,
            onCityChange = { city = it },
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Composable
fun AddAddressContent(
    modifier: Modifier = Modifier,
    name: String,
    onNameChange: (String) -> Unit,
    details: String,
    onDetailsChange: (String) -> Unit,
    phone: String,
    onPhoneChange: (String) -> Unit,
    city: String,
    onCityChange: (String) -> Unit,
) {
    val scheme = MaterialTheme.colorScheme

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ECommerceTextField(
            value = name,
            onValueChange = onNameChange,
            label = stringResource(R.string.name),
            labelColor = scheme.onBackground,
            colors = OutlinedTextFieldDefaults.onBackgroundColors()
        )

        ECommerceTextField(
            value = details,
            onValueChange = onDetailsChange,
            label = stringResource(R.string.details),
            labelColor = scheme.onBackground,
            colors = OutlinedTextFieldDefaults.onBackgroundColors()
        )

        ECommerceTextField(
            value = phone,
            onValueChange = onPhoneChange,
            label = stringResource(R.string.phone),
            labelColor = scheme.onBackground,
            colors = OutlinedTextFieldDefaults.onBackgroundColors(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            )
        )

        ECommerceTextField(
            value = city,
            onValueChange = onCityChange,
            label = stringResource(R.string.city),
            labelColor = scheme.onBackground,
            colors = OutlinedTextFieldDefaults.onBackgroundColors(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Go
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddAddressDialogPreview() {
    ECommerceTheme {
        AddAddressContent(
            name = "Home", onNameChange = {},
            details = "123 Main st", onDetailsChange = {},
            phone = "555-1234", onPhoneChange = {},
            city = "Anytown", onCityChange = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
