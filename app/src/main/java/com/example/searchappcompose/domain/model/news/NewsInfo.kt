package com.example.searchappcompose.domain.model.news

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "News")
data class NewsInfo(
    @PrimaryKey val id: String,
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