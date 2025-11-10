package com.route.e_commerce

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.domain.usecases.local_storage.GetTokenUseCase
import com.route.e_commerce.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val getTokenUseCase: GetTokenUseCase) :
    ViewModel() {

    private val _startScreen = MutableStateFlow<Screen?>(null)
    val startDestination = _startScreen.asStateFlow()

    init {
        viewModelScope.launch {
            val token = getTokenUseCase()
            _startScreen.value = if (token != null) Screen.Main else Screen.Auth
        }
    }
}
