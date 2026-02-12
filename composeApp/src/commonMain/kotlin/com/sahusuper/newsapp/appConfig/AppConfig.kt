package com.sahusuper.newsapp.appConfig

object AppConfig {
    const val BASE_URL = "https://newsdata.io/api/1/latest"
}

expect object KeyProvider{
    fun getApiKey(): String
}
