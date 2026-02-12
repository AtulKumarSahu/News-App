package com.sahusuper.newsapp.appConfig

import com.sahusuper.newsapp.BuildConfig

actual object KeyProvider {
    actual fun getApiKey(): String {
        return BuildConfig.API_KEY
    }
}