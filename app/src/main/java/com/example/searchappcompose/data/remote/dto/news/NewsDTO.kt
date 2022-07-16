package com.example.searchappcompose.data.remote.dto.news

data class NewsDTO(
    val id: String,
    val title: String,
    val url: String,
    val description: String,
    val body: String,
    val snippet: String,
    val datePublished: String,
    val image: ImageDTO
)