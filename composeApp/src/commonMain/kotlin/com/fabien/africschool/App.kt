package com.fabien.africschool

import androidx.compose.runtime.Composable
import com.fabien.africschool.di.AppModule
import com.fabien.africschool.ui.theme.AppTheme
import com.fabien.africschool.ui.theme.screens.login.LoginScreen
import com.fabien.africschool.ui.theme.screens.onboard.OnBoardScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import io.github.aakira.napier.Napier
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.ksp.generated.module

@Composable
@Preview
fun App() {
    val backStack = rememberSaveableBackStack(root = OnBoardScreen)
    val navigator =
        rememberCircuitNavigator(backStack) {
            Napier.d { "fabien" }
        }

    KoinApplication(application = {
        modules(AppModule().module)
    }) {
        AppTheme {
            CircuitCompositionLocals(circuit = koinInject()) {
                NavigableCircuitContent(navigator = navigator, backStack = backStack)
            }
        }
    }
}
