package com.sahusuper.newsapp.data.modal

import kotlinx.serialization.Serializable

@Serializable
data class NewsRes(
    val nextPage: String? = null,
    val results: List<Article>,
    val status: String,
    val totalResults: Int
)
