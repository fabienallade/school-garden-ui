package com.fabien.africschool.ui.theme.screens.onboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fabien.africschool.domain.state.ResponseState
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import io.github.aakira.napier.Napier

@Composable
fun OnBoardUi(
    state: OnBoardState,
    modifier: Modifier,
) {
    val user = state.user.collectAsState(ResponseState.Loading).value
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (user) {
            ResponseState.Loading -> Text("Loading...")
            is ResponseState.Success -> {
                Text("fabien")
                Text(user.data.lastName, color = MaterialTheme.colorScheme.error)
                Text(user.data.id, color = MaterialTheme.colorScheme.error)
                Text(user.data.firstName, color = MaterialTheme.colorScheme.error)
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
