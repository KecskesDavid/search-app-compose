package com.example.searchappcompose.app.screens.search

sealed class SearchEvent {
    data class OnQueryEntered(val query: String): SearchEvent()
    data class OnAppBarStateChange(val state: AppBarState): SearchEvent()
}