package com.fabien.africschool.di.modules

import com.fabien.africschool.ui.theme.screens.login.LoginPresenter
import com.fabien.africschool.ui.theme.screens.login.LoginScreen
import com.fabien.africschool.ui.theme.screens.login.LoginUi
import com.slack.circuit.foundation.Circuit
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class UiModule {
    @Single
    fun provideCircuit(): Circuit =
        Circuit
            .Builder()
            .addUi<LoginScreen, LoginScreen.State> { state, modifier ->
                LoginUi(state, modifier)
            }.addPresenterFactory(factory = LoginPresenter.Factory())
            .build()
}
