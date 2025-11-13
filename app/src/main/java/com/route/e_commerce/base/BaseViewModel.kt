package com.route.e_commerce.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE : ViewState, ACTION, EFFECT : ViewSideEffect>(
    initialState: STATE
) : ViewModel() {

    private val _viewState = MutableStateFlow(initialState)
    val viewState = _viewState.asStateFlow()

    private val _viewEffect = MutableSharedFlow<EFFECT>()
    val viewEffect = _viewEffect.asSharedFlow()


    fun onAction(action: ACTION) {
        viewModelScope.launch {
            handleAction(action)
        }
    }

    protected abstract suspend fun handleAction(action: ACTION)

    protected fun updateState(newState: STATE) {
        _viewState.value = newState
    }

    protected fun sendEffect(effect: EFFECT) {
        viewModelScope.launch {
            _viewEffect.emit(effect)
        }
    }
}
