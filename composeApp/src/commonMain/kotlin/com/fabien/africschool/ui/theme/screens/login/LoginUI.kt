package com.fabien.africschool.ui.theme.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginUi(
    state: LoginScreen.State,
    modifier: Modifier,
) {
    var username by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Login")
                },
            )
        },
        bottomBar = {
            BottomAppBar {
                Text("Login")
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Outlined.RemoveRedEye, contentDescription = "")
            }
        },
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                BoxWithConstraints(modifier = Modifier.fillMaxWidth(fraction = 0.7f)) {
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
                                leadingIcon = {
                                    Icon(imageVector = Icons.Outlined.RemoveRedEye, contentDescription = "")
                                },
                                trailingIcon = {
                                    Icon(imageVector = Icons.Outlined.Close, contentDescription = "")
                                },
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = username,
                                onValueChange = { username = it },
                                label = { Text("Password") },
                                leadingIcon = {
                                    Icon(imageVector = Icons.Outlined.RemoveRedEye, contentDescription = "")
                                },
                                trailingIcon = {
                                    Icon(imageVector = Icons.Outlined.Close, contentDescription = "")
                                },
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(modifier = Modifier.fillMaxWidth(), onClick = {}) {
                                Text("Se connecter", style = MaterialTheme.typography.headlineSmall)
                            }
                        }
                    }
                }
            }
        }
    }
}
