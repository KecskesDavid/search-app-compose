package com.example.searchappcompose.app.screens.search

sealed class SearchScreenEvent {
    data class OnQueryEntered(val query: String): SearchScreenEvent()
    data class OnAppBarStateChange(val state: AppBarState): SearchScreenEvent()
}