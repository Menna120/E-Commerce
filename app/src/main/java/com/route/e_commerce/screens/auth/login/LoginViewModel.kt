package com.route.e_commerce.screens.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.domain.base.Resource
import com.route.domain.usecases.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun login() {
        viewModelScope.launch {
            loginUseCase(
                email = uiState.email,
                password = uiState.password
            ).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        uiState = uiState.copy(isLoading = true, error = null)
                    }

                    is Resource.Success -> {
                        uiState = uiState.copy(isLoading = false, loginSuccess = true)
                    }

                    is Resource.Error -> {
                        uiState = uiState.copy(isLoading = false, error = resource.errorMessage)
                    }

                    else -> {}
                }
            }
        }
    }
}
