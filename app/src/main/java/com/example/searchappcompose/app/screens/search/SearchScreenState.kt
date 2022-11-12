package com.example.searchappcompose.app.screens.search

import com.example.searchappcompose.domain.model.news.News
import com.example.searchappcompose.domain.model.news.NewsInfo

data class SearchScreenState(
    val news: List<NewsInfo>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)