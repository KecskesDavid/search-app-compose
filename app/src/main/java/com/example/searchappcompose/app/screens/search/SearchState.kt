package com.example.searchappcompose.app.screens.search

import com.example.searchappcompose.app.core.models.SearchCategory
import com.example.searchappcompose.domain.model.news.NewsInfo

data class SearchScreenState(
    val news: List<NewsInfo>? = null,
    val filters: List<SearchCategory>? = SearchCategory.getList(),
    val searchQuery: String = "",
    val appBarState: AppBarState = AppBarState.CLOSED,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

enum class AppBarState {
    OPENED, CLOSED
}