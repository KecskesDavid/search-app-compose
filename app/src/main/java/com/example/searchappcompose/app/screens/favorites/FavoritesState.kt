package com.example.searchappcompose.app.screens.favorites

import com.example.searchappcompose.domain.model.news.NewsInfo

data class FavoritesState(
    val favorites: List<NewsInfo>? = null,
    val isLoading: Boolean = false,
)