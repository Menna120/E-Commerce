package com.route.e_commerce.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : ViewState, E : ViewEvent, F : ViewSideEffect>(
    initialState: S
) : ViewModel() {

    private val _viewState = MutableStateFlow(initialState)
    val viewState = _viewState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<E>()
    private val viewEvent = _viewEvent.asSharedFlow()

    private val _viewEffect = MutableSharedFlow<F>()
    val viewEffect = _viewEffect.asSharedFlow()

    init {
        subscribeToEvents()
    }

    fun setEvent(event: E) {
        viewModelScope.launch { _viewEvent.emit(event) }
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            viewEvent.collect {
                handleEvent(it)
            }
        }
    }

    protected fun setState(newState: S.() -> S) {
        _viewState.value = viewState.value.newState()
    }

    protected fun setEffect(effect: () -> F) {
        viewModelScope.launch { _viewEffect.emit(effect()) }
    }

    protected abstract fun handleEvent(event: E)
}
