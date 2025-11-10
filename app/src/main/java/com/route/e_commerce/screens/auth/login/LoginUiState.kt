package com.route.e_commerce.screens.auth.login

data class LoginUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val loginSuccess: Boolean = false,
    val email: String = "",
    val password: String = ""
) {
    val enableLoginButton: Boolean
        get() = email.isNotBlank() && password.isNotBlank()
}
