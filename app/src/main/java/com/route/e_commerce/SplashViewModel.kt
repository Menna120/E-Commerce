package com.route.e_commerce

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.e_commerce.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _startDestination = MutableStateFlow<Destination?>(null)
    val startDestination = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            // TODO: check if user is logged in
            _startDestination.value = Destination.Auth
        }
    }
}
