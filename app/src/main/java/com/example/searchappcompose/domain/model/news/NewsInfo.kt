package com.example.searchappcompose.domain.model.news

import com.example.searchappcompose.data.remote.dto.news.ImageDTO

data class NewsInfo(
    val id: String,
    val title: String,
    val url: String,
    val description: String,
    val body: String,
    val snippet: String,
    val datePublished: String,
    val imageUrl: String,
    val imageThumbnail: String,
    var isFavorite: Boolean = false
)