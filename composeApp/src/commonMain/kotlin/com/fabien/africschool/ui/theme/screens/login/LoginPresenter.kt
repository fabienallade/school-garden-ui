package com.fabien.africschool.ui.theme.screens.login

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen

class LoginPresenter(
    val navigator: Navigator,
) : Presenter<LoginState> {
    @Composable
    override fun present(): LoginState =
        LoginState { event ->
            when (event) {
                LoginScreen.Event.LoginEvent -> navigator.pop()
            }
        }

    class Factory : Presenter.Factory {
        override fun create(
            screen: Screen,
            navigator: Navigator,
            context: CircuitContext,
        ): Presenter<*>? =
            when (screen) {
                is LoginScreen -> LoginPresenter(navigator = navigator)
                else -> null
            }
    }
}
