package com.fabien.africschool.di

import com.fabien.africschool.di.modules.NetworkModule
import com.fabien.africschool.di.modules.PlatformModule
import com.fabien.africschool.di.modules.RepositoryModule
import com.fabien.africschool.di.modules.ServiceModule
import com.fabien.africschool.di.modules.UiModule
import com.fabien.africschool.ui.theme.screens.login.LoginPresenter
import com.fabien.africschool.ui.theme.screens.login.LoginScreen
import com.fabien.africschool.ui.theme.screens.login.LoginUi
import com.slack.circuit.foundation.Circuit
import org.koin.core.KoinApplication
import org.koin.core.annotation.Module
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.koin.ksp.generated.module

fun initApplication(appDeclaration: KoinAppDeclaration = {}): KoinApplication =
    startKoin {
        appDeclaration()
        modules(
            AppModule().module,
        )
    }

@Module(
    includes = [
        NetworkModule::class,
        RepositoryModule::class,
        PlatformModule::class,
        ServiceModule::class,
        UiModule::class,
    ],
)
class AppModule
