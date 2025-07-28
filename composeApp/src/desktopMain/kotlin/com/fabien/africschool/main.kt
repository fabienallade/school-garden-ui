package com.fabien.africschool

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.fabien.africschool.di.AppModule
import com.fabien.africschool.di.initkoin
import com.slack.circuit.foundation.Circuit
import org.koin.compose.getKoin
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

fun main() =
    application {
//    initkoin()
        Window(
            onCloseRequest = ::exitApplication,
            title = "AfricSchool",
        ) {
            App()
        }
    }
