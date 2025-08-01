package com.fabien.africschool.ui.theme.screens.onboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.fabien.africschool.domain.state.ResponseState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import io.github.aakira.napier.Napier

@Composable
fun OnBoardUi(
    state: OnBoardState,
    modifier: Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (state.user) {
            ResponseState.Loading -> Text("Loading...")
            is ResponseState.Success -> {
                Text("Hello world!")
            }
            is ResponseState.Error -> {
                Text("Something went wrong")
            }
        }

        Text("Count: ${state.count}")
        Button(onClick = { state.eventSink(OnBoardEvent.Increment) }) {
            Text("+")
        }
        Button(onClick = { state.eventSink(OnBoardEvent.Decrement) }) {
            Text("-")
        }
        Button(onClick = { state.eventSink(OnBoardEvent.Reset) }) {
            Text("reset")
        }
        Button(onClick = { state.eventSink(OnBoardEvent.NavigateLoginEvent) }) {
            Text("login")
        }
    }
}
