package com.fabien.africschool.di.modules

import com.fabien.africschool.data.network.ApiService
import com.fabien.africschool.data.repository.AuthRepository
import com.fabien.africschool.ui.theme.screens.login.LoginPresenter
import com.fabien.africschool.ui.theme.screens.login.LoginScreen
import com.fabien.africschool.ui.theme.screens.login.LoginState
import com.fabien.africschool.ui.theme.screens.login.LoginUi
import com.fabien.africschool.ui.theme.screens.onboard.OnBoardPresenter
import com.fabien.africschool.ui.theme.screens.onboard.OnBoardScreen
import com.fabien.africschool.ui.theme.screens.onboard.OnBoardState
import com.fabien.africschool.ui.theme.screens.onboard.OnBoardUi
import com.slack.circuit.foundation.Circuit
import org.koin.compose.koinInject
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.fabien.africschool.data.repository")
class UiModule {
    @Single
    fun providesCircuit(authRepository: AuthRepository): Circuit =
        Circuit
            .Builder()
            .addUi<LoginScreen, LoginState> { state, modifier ->
                LoginUi(state, modifier)
            }.addPresenterFactory(factory = LoginPresenter.Factory())
            .addUi<OnBoardScreen, OnBoardState> { state, modifier ->
                OnBoardUi(state, modifier)
            }.addPresenterFactory(factory = OnBoardPresenter.Factory(authRepository))
            .build()
}
