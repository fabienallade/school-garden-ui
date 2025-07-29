package com.fabien.africschool.di.modules

import com.fabien.africschool.ui.theme.screens.login.LoginPresenter
import com.fabien.africschool.ui.theme.screens.login.LoginScreen
import com.fabien.africschool.ui.theme.screens.login.LoginState
import com.fabien.africschool.ui.theme.screens.login.LoginUi
import com.fabien.africschool.ui.theme.screens.onboard.OnBoardPresenter
import com.fabien.africschool.ui.theme.screens.onboard.OnBoardScreen
import com.fabien.africschool.ui.theme.screens.onboard.OnBoardState
import com.fabien.africschool.ui.theme.screens.onboard.OnBoardUi
import com.slack.circuit.foundation.Circuit
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class UiModule {
    @Single
    fun provideCircuit(): Circuit =
        Circuit
            .Builder()
            .addUi<LoginScreen, LoginState> { state, modifier ->
                LoginUi(state, modifier)
            }.addPresenterFactory(factory = LoginPresenter.Factory())
            .addUi<OnBoardScreen, OnBoardState> { state, modifier ->
                OnBoardUi(state, modifier)
            }.addPresenterFactory(factory = OnBoardPresenter.Factory())
            .build()
}
