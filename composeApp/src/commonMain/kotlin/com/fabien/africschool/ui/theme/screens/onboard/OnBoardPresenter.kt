package com.fabien.africschool.ui.theme.screens.onboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.fabien.africschool.data.model.User
import com.fabien.africschool.data.repository.AuthRepository
import com.fabien.africschool.domain.state.ResponseState
import com.fabien.africschool.ui.theme.screens.login.LoginScreen
import com.slack.circuit.retained.collectAsRetainedState
import com.slack.circuit.retained.rememberRetainedSaveable
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Single

@Single
class OnBoardPresenter(
    @InjectedParam val navigator: Navigator,
    private val authRepository: AuthRepository,
) : Presenter<OnBoardState> {
    @Composable
    override fun present(): OnBoardState {
        var counter by rememberSaveable { mutableStateOf(0) }

        val lifecycleOwner = LocalLifecycleOwner.current
        val coroutineScope = rememberCoroutineScope()
        var user: Flow<ResponseState<List<User>>> by rememberSaveable { mutableStateOf(flowOf()) }

        LaunchedEffect(Unit) {
            Napier.d("Napier started")
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                coroutineScope.launch {
                    authRepository.getUsers().let { response -> user = response }
                }
            }
        }

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
        val authRepository: AuthRepository,
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
