package com.example.searchappcompose.app.screens.search

import com.example.searchappcompose.domain.model.news.NewsInfo

sealed class SearchEvent {
    data class OnQueryEntered(val query: String) : SearchEvent()
    data class OnAppBarStateChange(val state: AppBarState) : SearchEvent()
    data class OnFavoritesClicked(val newsInfo: NewsInfo) : SearchEvent()
}