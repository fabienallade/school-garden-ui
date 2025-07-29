package com.fabien.africschool.ui.theme.screens.login

import com.fabien.africschool.utils.Parcelize
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen

@Parcelize
data object LoginScreen : Screen {
    data class State(
        val eventSink: (CircuitUiEvent) -> Unit,
    ) : CircuitUiState

    sealed class Event : CircuitUiEvent {
        data object LoginEvent : Event()
    }
}

data class LoginState(
    val eventSink: (LoginEvent) -> Unit,
) : CircuitUiState

sealed interface LoginEvent : CircuitUiEvent {
    data object LoginButton : LoginEvent
}
