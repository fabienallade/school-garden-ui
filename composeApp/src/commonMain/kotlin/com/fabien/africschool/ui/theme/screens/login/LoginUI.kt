package com.fabien.africschool.ui.theme.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoginUi(
    state: LoginState,
    modifier: Modifier,
) {
    var username by remember { mutableStateOf("") }
    Scaffold {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                )
                BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                    OutlinedCard(
                        modifier = Modifier,
                        elevation = CardDefaults.elevatedCardElevation(8.dp),
                    ) {
                        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                            Column(
                                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    "Se connecter",
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = MaterialTheme.colorScheme.secondary,
                                )
                                Text("Vous n'avez pas encore de compte ??")
                            }
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = username,
                                onValueChange = { username = it },
                                label = { Text("Username") },
                                maxLines = 1,
                                leadingIcon = {
                                    Icon(imageVector = Icons.Outlined.Email, contentDescription = "")
                                },
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = username,
                                onValueChange = { username = it },
                                label = { Text("Password") },
                                maxLines = 1,
                                leadingIcon = {
                                    Icon(imageVector = Icons.Outlined.Password, contentDescription = "")
                                },
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(modifier = Modifier.fillMaxWidth(), onClick = {}) {
                                Text("Se connecter", style = MaterialTheme.typography.headlineSmall)
                            }
                            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                                state.eventSink(LoginEvent.LoginButton)
                            }) {
                                Text("Go back")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun LoginUiPreview() {
    LoginUi(
        state = LoginState({}),
        modifier = Modifier,
    )
}
