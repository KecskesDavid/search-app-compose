package com.example.searchappcompose.app.screens.favorites

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchappcompose.app.screens.search.SearchViewModel
import com.example.searchappcompose.domain.model.news.NewsInfo
import com.example.searchappcompose.domain.use_case.get_favorites.GetFavoritesUseCase
import com.example.searchappcompose.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class FavoritesViewModel @Inject constructor(
    private val getFavorites: GetFavoritesUseCase,
) : ViewModel() {

    var state by mutableStateOf(FavoritesState())
        private set

    fun onEvent(event: FavoritesEvent) {
        when (event) {
            is FavoritesEvent.OnFavoritesClicked -> {}
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