package com.example.searchappcompose.app.screens.favorites

import com.example.searchappcompose.domain.model.news.NewsInfo

sealed class FavoritesEvent {
    data class OnFavoritesClicked(val news: NewsInfo) : FavoritesEvent()
    object OnDeleteDialogDismiss : FavoritesEvent()
    object OnDeleteDialogConfirm : FavoritesEvent()
}