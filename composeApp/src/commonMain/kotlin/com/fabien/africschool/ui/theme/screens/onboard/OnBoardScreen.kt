package com.fabien.africschool.ui.theme.screens.onboard

import com.fabien.africschool.utils.Parcelize
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen

@Parcelize
data object OnBoardScreen : Screen

data class OnBoardState(
    val count: Int,
    val eventSink: (OnBoardEvent) -> Unit,
) : CircuitUiState

sealed interface OnBoardEvent : CircuitUiEvent {
    data object Increment : OnBoardEvent

    data object Decrement : OnBoardEvent

    data object Reset : OnBoardEvent

    data object NavigateLoginEvent : OnBoardEvent
}
