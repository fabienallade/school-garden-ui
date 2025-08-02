package com.fabien.africschool.di.modules

import com.fabien.africschool.data.network.ApiService
import com.fabien.africschool.data.network.createApiService
import de.jensklingenberg.ktorfit.Ktorfit
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class NetworkModule {
    @Single
    fun provideHttpClient(): HttpClient =
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                    },
                )
            }
            install(Logging) {
                logger =
                    object : Logger {
                        override fun log(message: String) {
                            Napier.v("HTTP Client", null, message)
                        }
                    }
                level = LogLevel.HEADERS
                sanitizeHeader { header -> header == HttpHeaders.Authorization }
            }
        }

    @Single
    fun provideKtorfit(client: HttpClient): Ktorfit =
        Ktorfit
            .Builder()
            .baseUrl("http://localhost:8080/api/")
            .httpClient(client)
            .build()

    @Single
    fun provideApiService(ktorfit: Ktorfit): ApiService = ktorfit.createApiService()
}
