package com.evoionosp.dogs.data.remote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import com.evoionosp.dogs.BuildConfig;
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.plugins.resources.Resources
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    @Singleton
//    @Provides
//    fun provideHttpClient(): HttpClient  = HttpClient(OkHttp) {
//            install(Logging) {
//                logger = Logger.SIMPLE
//                level = if(BuildConfig.DEBUG) LogLevel.ALL else LogLevel.HEADERS
//            }
//        }

    @Provides
    @Singleton
    fun provideClient(): HttpClient {
        return HttpClient(OkHttp) {
            // 1. Install Type-Safe Resources
            install(Resources)

            // 2. Install JSON Serialization for Data Payloads
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                    encodeDefaults = true
                    explicitNulls = false
                })
            }

            install(Logging) {
                this.level = LogLevel.ALL
                this.logger = Logger.SIMPLE
            }
        }
    }


    @Singleton
    @Provides
    fun provideApiService(client: HttpClient): ApiService = ApiServiceImpl(client)

}