package com.fabien.africschool.ui.theme.screens.onboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.fabien.africschool.data.repository.AuthRepository
import com.fabien.africschool.domain.state.ResponseState
import com.fabien.africschool.ui.theme.screens.login.LoginScreen
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import io.github.aakira.napier.Napier
import org.koin.core.annotation.InjectedParam

class OnBoardPresenter(
    val navigator: Navigator,
    private val authRepository: AuthRepository,
) : Presenter<OnBoardState> {
    @Composable
    override fun present(): OnBoardState {
        var counter by rememberSaveable { mutableStateOf(0) }
        val user by authRepository.getUser("").collectAsState(initial = ResponseState.Loading)

        return OnBoardState(counter, user) { event ->
            when (event) {
                OnBoardEvent.Increment -> {
                    counter++
                }
                OnBoardEvent.Decrement -> {
                    counter--
                }
                OnBoardEvent.Reset -> {
                    counter = 0
                }
                OnBoardEvent.NavigateLoginEvent -> navigator.goTo(LoginScreen)
            }
        }
    }

    class Factory(
        private val authRepository: AuthRepository,
    ) : Presenter.Factory {
        override fun create(
            screen: Screen,
            navigator: Navigator,
            context: CircuitContext,
        ): Presenter<*>? =
            when (screen) {
                is OnBoardScreen -> OnBoardPresenter(navigator = navigator, authRepository)
                else -> null
            }
    }
}
