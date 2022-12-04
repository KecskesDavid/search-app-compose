package com.example.searchappcompose.app.screens.favorites

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchappcompose.app.screens.search.SearchViewModel
import com.example.searchappcompose.domain.model.news.NewsInfo
import com.example.searchappcompose.domain.use_case.delete_from_favorites.DeleteFromFavoritesUseCase
import com.example.searchappcompose.domain.use_case.get_favorites.GetFavoritesUseCase
import com.example.searchappcompose.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class FavoritesViewModel @Inject constructor(
    private val getFavorites: GetFavoritesUseCase,
    private val deleteFromFavorites: DeleteFromFavoritesUseCase
) : ViewModel() {

    var state by mutableStateOf(FavoritesState())
        private set

    fun onEvent(event: FavoritesEvent) {
        when (event) {
            is FavoritesEvent.OnFavoritesClicked -> {
                state = state.copy(
                    showDeleteDialog = true, newsToDelete = event.news
                )
            }

            FavoritesEvent.OnDeleteDialogConfirm -> {
                state.newsToDelete?.let {
                    handleDeleteFromFavorites(it)
                }
            }

            FavoritesEvent.OnDeleteDialogDismiss -> {
                state = state.copy(
                    showDeleteDialog = false, newsToDelete = null
                )
            }
        }
    }

    private fun handleDeleteFromFavorites(newsInfo: NewsInfo) {
        state = state.copy(
            showDeleteDialog = false,
            newsToDelete = null,
            favorites = state.favorites?.filter { it.id != newsInfo.id }
        )

        viewModelScope.launch {
            deleteFromFavorites(newsInfo)
        }
    }

    fun getFavoriteNews() {
        viewModelScope.launch {
            getFavorites().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        state = state.copy(
                            favorites = (result.data as List<NewsInfo>),
                            isLoading = false,
                        )
                        Log.i(SearchViewModel.TAG, state.favorites?.size.toString())
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            favorites = null, isLoading = false
                        )
                        Log.i(SearchViewModel.TAG, "Error: " + result.message.toString())
                    }

                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                        Log.i(SearchViewModel.TAG, "Loading: " + result.message.toString())
                    }
                }
            }
        }
    }

}