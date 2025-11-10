package com.route.e_commerce.screens.auth.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.domain.base.Resource
import com.route.domain.usecases.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    var uiState by mutableStateOf(RegisterUiState())
        private set

    fun onFullNameChange(fullName: String) {
        uiState = uiState.copy(fullName = fullName)
    }

    fun onMobileNumberChange(mobileNumber: String) {
        uiState = uiState.copy(mobileNumber = mobileNumber)
    }

    fun onEmailAddressChange(emailAddress: String) {
        uiState = uiState.copy(emailAddress = emailAddress)
    }

    fun onPasswordChange(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun register() {
        viewModelScope.launch {
            registerUseCase(
                name = uiState.fullName,
                email = uiState.emailAddress,
                password = uiState.password,
                rePassword = uiState.password,
                phone = uiState.mobileNumber
            ).collect {
                when (it) {
                    is Resource.Loading -> {
                        uiState = uiState.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        uiState = uiState.copy(isLoading = false, isRegistered = true)
                    }

                    is Resource.Error -> {
                        uiState = uiState.copy(isLoading = false, error = it.errorMessage)
                    }

                    else -> {}
                }
            }
        }
    }
}
