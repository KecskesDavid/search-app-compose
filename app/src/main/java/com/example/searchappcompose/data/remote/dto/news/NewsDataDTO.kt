package com.example.searchappcompose.data.remote.dto.news

import com.squareup.moshi.Json

data class NewsDataDTO(
    @field:Json(name="value")
    val value: List<NewsDTO>
)