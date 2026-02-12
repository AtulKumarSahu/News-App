package com.sahusuper.newsapp.data.repo

import com.sahusuper.newsapp.data.api.HttpClientFactory
import com.sahusuper.newsapp.data.modal.NewsRes
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepo(
) {


    val client = HttpClientFactory.create()

    suspend fun getNews(type: String, query: String?): Result<NewsRes> {

        return runCatching {
            (client.get {
                url {
                    appendPathSegments(type)
                }
                if (query != null) parameter("q", query)
            }.body<NewsRes>())

        }

    }


    fun getNews1(type: String, query: String?): Flow<NewsRes> {

        return flow {

            val response = client.get {
                url {
                    appendPathSegments(type)
                }
                if (query != null) parameter("q", query)
            }.body<NewsRes>()

            emit(response)

        }

    }


}

