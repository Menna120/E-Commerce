package com.route.e_commerce.screens.auth.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.route.e_commerce.R
import com.route.e_commerce.components.RouteLogo
import com.route.e_commerce.navigation.Screen
import com.route.e_commerce.screens.auth.common.AuthButton
import com.route.e_commerce.components.ECommerceTextField
import com.route.e_commerce.components.onPrimaryColors
import com.route.e_commerce.screens.auth.common.LoadingIndicator
import com.route.e_commerce.ui.theme.ECommerceTheme
import com.route.e_commerce.utils.LocalAppNavController

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState
    val navController = LocalAppNavController.current
    val context = LocalContext.current

    LaunchedEffect(uiState.loginSuccess, uiState.error) {
        if (uiState.loginSuccess) {
            Toast.makeText(
                context,
                context.getString(R.string.logged_in_successfully), Toast.LENGTH_SHORT
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
        LoginContent(
            state = uiState,
            modifier = modifier,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onLogin = viewModel::login,
        )

        if (uiState.isLoading) {
            LoadingIndicator()
        }
    }
}

@Composable
fun LoginContent(
    state: LoginUiState,
    modifier: Modifier = Modifier,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit
) {
    val scheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    val navController = LocalAppNavController.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(scheme.primary)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))

        RouteLogo(modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = typography.headlineSmall.toSpanStyle()
                        .copy(fontWeight = FontWeight.SemiBold)
                ) {
                    append(stringResource(R.string.welcome_back_to_route))
                }

                withStyle(
                    style = typography.titleMedium.toSpanStyle()
                        .copy(fontWeight = FontWeight.Normal)
                ) {
                    append("\n" + stringResource(R.string.please_sign_in_with_your_mail))
                }
            },
            color = scheme.onPrimary,
        )

        Spacer(modifier = Modifier.weight(.5f))

        ECommerceTextField(
            value = state.email,
            onValueChange = onEmailChange,
            label = stringResource(R.string.user_name),
            placeholderText = stringResource(R.string.enter_your_name),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            colors = OutlinedTextFieldDefaults.onPrimaryColors()
        )

        Spacer(modifier = Modifier.weight(.25f))

        ECommerceTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            label = stringResource(R.string.password),
            placeholderText = stringResource(R.string.enter_your_password),
            isPassword = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            colors = OutlinedTextFieldDefaults.onPrimaryColors()
        )

        Text(
            text = stringResource(R.string.forgot_password),
            style = typography.bodyMedium,
            color = scheme.onPrimary,
            modifier = Modifier.align(Alignment.End)
        )

        Spacer(modifier = Modifier.weight(.5f))

        AuthButton(
            onClick = onLogin,
            text = stringResource(R.string.login),
            modifier = Modifier.fillMaxWidth(),
            enabled = state.enableLoginButton
        )

        Spacer(modifier = Modifier.weight(.25f))

        TextButton(
            onClick = { navController.navigate(Screen.Register) },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.textButtonColors(contentColor = scheme.onPrimary)
        ) {
            Text(
                text = stringResource(R.string.don_t_have_an_account_create_account),
                style = typography.bodyMedium.copy(letterSpacing = (-.6).sp),
                textAlign = TextAlign.Center
            )
        }


        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ECommerceTheme {
        CompositionLocalProvider(LocalAppNavController provides rememberNavController()) {
            LoginContent(
                state = LoginUiState(),
                onEmailChange = {},
                onPasswordChange = {},
                onLogin = {}
            )
        }
    }
}
