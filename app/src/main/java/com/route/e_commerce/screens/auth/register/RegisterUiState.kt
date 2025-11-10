package com.route.e_commerce.screens.auth.register

data class RegisterUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRegistered: Boolean = false,
    val fullName: String = "",
    val mobileNumber: String = "",
    val emailAddress: String = "",
    val password: String = "",
) {
    val enableRegisterButton: Boolean
        get() = fullName.isNotBlank() && mobileNumber.isNotBlank() && emailAddress.isNotBlank() && password.isNotBlank()
}
