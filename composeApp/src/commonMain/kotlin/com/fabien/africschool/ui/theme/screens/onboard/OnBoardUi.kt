package com.fabien.africschool.ui.theme.screens.onboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fabien.africschool.domain.state.ResponseState
import com.fabien.africschool.ui.theme.Themes
import com.fabien.africschool.ui.theme.Themes.isCompact
import com.fabien.africschool.utils.AdaptiveLayoutType
import com.fabien.africschool.utils.ContentType
import com.slack.circuit.retained.collectAsRetainedState

@Composable
fun OnBoardUi(
    state: OnBoardState,
    modifier: Modifier,
) {
    val users = state.user.collectAsRetainedState(ResponseState.Loading).value
    val scrollable = rememberLazyGridState()

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (users) {
            ResponseState.Loading -> {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.secondary,
                )
                Text("Loading...")
            }
            is ResponseState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(if (Themes.isCompact()) 1 else 6),
                    state = scrollable,
                ) {
                    items(users.data.size) { index ->
                        val user = users.data[index]

                        BadgedBox(
                            modifier = Modifier.fillMaxSize().padding(12.dp),
                            badge = {
                                Badge {
                                    Text("${state.count}")
                                }
                            },
                        ) {
                            ElevatedCard(
                                modifier = Modifier.fillMaxSize().padding(8.dp),
                                elevation = CardDefaults.elevatedCardElevation(2.dp),
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Id : " + user.id.toString())
                                    Text("Nom : " + user.lastName.toString())
                                    Text("Prenoms : " + user.firstName.toString())
                                }
                            }
                        }
                    }
                }
                Text("fabien")
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
