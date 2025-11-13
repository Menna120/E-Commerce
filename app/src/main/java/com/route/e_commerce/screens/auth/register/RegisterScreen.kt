package com.route.e_commerce.screens.auth.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.route.e_commerce.R
import com.route.e_commerce.components.ECommerceTextField
import com.route.e_commerce.components.RouteLogo
import com.route.e_commerce.components.onPrimaryColors
import com.route.e_commerce.navigation.Screen
import com.route.e_commerce.screens.auth.common.AuthButton
import com.route.e_commerce.screens.auth.common.LoadingIndicator
import com.route.e_commerce.ui.theme.ECommerceTheme
import com.route.e_commerce.utils.LocalAppNavController

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val navController = LocalAppNavController.current
    val uiState = viewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(key1 = uiState.isRegistered, key2 = uiState.error) {
        if (uiState.isRegistered) {
            Toast.makeText(
                context,
                context.getString(R.string.registration_successful), Toast.LENGTH_SHORT
            ).show()

            navController.navigate(Screen.Main) {
                popUpTo(Screen.Auth) { inclusive = true }
            }
        }
        uiState.error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        RegisterContent(
            modifier = modifier,
            state = uiState,
            onFullNameChanged = viewModel::onFullNameChange,
            onMobileNumberChanged = viewModel::onMobileNumberChange,
            onEmailAddressChanged = viewModel::onEmailAddressChange,
            onPasswordChanged = viewModel::onPasswordChange,
            onRegisterClicked = viewModel::register
        )
        if (uiState.isLoading) {
            LoadingIndicator()
        }
    }
}

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    state: RegisterUiState,
    onFullNameChanged: (String) -> Unit,
    onMobileNumberChanged: (String) -> Unit,
    onEmailAddressChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onRegisterClicked: () -> Unit
) {
    val scheme = MaterialTheme.colorScheme

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(scheme.primary)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        RouteLogo()

        Spacer(modifier = Modifier.weight(1f))

        ECommerceTextField(
            value = state.fullName,
            onValueChange = onFullNameChanged,
            label = stringResource(R.string.full_name),
            placeholderText = stringResource(R.string.enter_your_full_name),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            colors = OutlinedTextFieldDefaults.onPrimaryColors()
        )

        Spacer(modifier = Modifier.weight(.2f))

        ECommerceTextField(
            value = state.mobileNumber,
            onValueChange = onMobileNumberChanged,
            label = stringResource(R.string.mobile_number),
            placeholderText = stringResource(R.string.enter_your_mobile_no),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            colors = OutlinedTextFieldDefaults.onPrimaryColors()
        )

        Spacer(modifier = Modifier.weight(.2f))

        ECommerceTextField(
            value = state.emailAddress,
            onValueChange = onEmailAddressChanged,
            label = stringResource(R.string.e_mail_address),
            placeholderText = stringResource(R.string.enter_your_email_address),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            colors = OutlinedTextFieldDefaults.onPrimaryColors()
        )

        Spacer(modifier = Modifier.weight(.2f))

        ECommerceTextField(
            value = state.password,
            onValueChange = onPasswordChanged,
            label = stringResource(R.string.password),
            placeholderText = stringResource(R.string.enter_your_password),
            isPassword = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            colors = OutlinedTextFieldDefaults.onPrimaryColors()
        )

        Spacer(modifier = Modifier.weight(.5f))

        AuthButton(
            onRegisterClicked,
            stringResource(R.string.sign_up),
            enabled = state.enableRegisterButton
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    ECommerceTheme {
        CompositionLocalProvider(LocalAppNavController provides rememberNavController()) {
            val state = remember { mutableStateOf(RegisterUiState()) }

            RegisterContent(
                state = state.value,
                onFullNameChanged = {},
                onMobileNumberChanged = {},
                onEmailAddressChanged = {},
                onPasswordChanged = {},
                onRegisterClicked = {}
            )
        }
    }
}
