package com.sahusuper.newsapp.data.api

import com.sahusuper.newsapp.appConfig.AppConfig
import com.sahusuper.newsapp.appConfig.KeyProvider
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.parseQueryString
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


object HttpClientFactory {

    fun create(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        explicitNulls = false
                    }
                )
            }

            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "newsdata.io"
                    path("api/1/")
                    parameters.append("apikey", KeyProvider.getApiKey())
                }
                contentType(ContentType.Application.Json)
            }

        }
    }
}