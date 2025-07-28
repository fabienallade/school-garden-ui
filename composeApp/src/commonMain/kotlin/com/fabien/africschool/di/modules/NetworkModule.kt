package com.fabien.africschool.di.modules

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class NetworkModule {
    @Single
    fun provideHttpClient(): HttpClient =
        HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }

    @Single
    fun provideKtorfit(client: HttpClient): Ktorfit =
        Ktorfit
            .Builder()
            .baseUrl("https://www.facebook.com")
            .httpClient(client)
            .build()
}
