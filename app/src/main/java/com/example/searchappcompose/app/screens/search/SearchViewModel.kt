package com.example.searchappcompose.app.screens.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchappcompose.app.core.models.SearchCategory
import com.example.searchappcompose.domain.model.news.News
import com.example.searchappcompose.domain.model.news.NewsInfo
import com.example.searchappcompose.domain.use_case.add_to_favorites.AddToFavoritesUseCase
import com.example.searchappcompose.domain.use_case.delete_from_favorites.DeleteFromFavoritesUseCase
import com.example.searchappcompose.domain.use_case.get_news.GetNewsUseCase
import com.example.searchappcompose.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getNews: GetNewsUseCase,
    private val addToFavorites: AddToFavoritesUseCase,
    private val deleteFromFavorites: DeleteFromFavoritesUseCase
) : ViewModel() {

    companion object {
        private var pageSize = 10
        const val pageNumber = 1
        const val searchDelay = 1000L
        const val TAG = "SearchViewModel"
    }

    var state by mutableStateOf(SearchScreenState())
        private set

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnQueryEntered -> {
                val searchQuery = event.query

                state = state.copy(
                    searchQuery = searchQuery
                )
                getNews(searchQuery)
            }

            is SearchEvent.OnAppBarStateChange -> {
                state = state.copy(
                    appBarState = event.state
                )
            }

            is SearchEvent.OnFavoritesClicked -> {
                handleFavoriteClick(event.newsInfo)
            }

            is SearchEvent.OnCategoryClicked -> {
                handleSearchCategoryClick(event.category)
            }
        }
    }

    private fun handleSearchCategoryClick(category: SearchCategory) {
        category.isSelected = !category.isSelected

        state = state.copy(
            filters = state.filters?.map {
                if(it == category) {
                    category
                } else it
            }
        )
    }

    private fun handleFavoriteClick(newsInfo: NewsInfo) {
        val updatedNews = newsInfo.copy(
            isFavorite = !newsInfo.isFavorite
        )

        state = state.copy(news = state.news?.map { news ->
            if (news.id == updatedNews.id) {
                updatedNews
            } else news
        })

        viewModelScope.launch {
            if (updatedNews.isFavorite) {
                addToFavorites(updatedNews)
            } else {
                deleteFromFavorites(updatedNews)
            }
        }
    }

    private fun getNews(searchQuery: String) {
        viewModelScope.launch {
            // Searching for the query
            getNews(searchQuery, pageNumber, pageSize).collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            state = state.copy(
                                news = (result.data as News).news,
                                isLoading = false,
                                errorMessage = null
                            )
                            Log.i(TAG, state.news?.size.toString())
                        }

                        is Resource.Error -> {
                            state = state.copy(
                                news = null, isLoading = false, errorMessage = result.message
                            )
                            Log.i(TAG, "Error: " + result.message.toString())
                        }

                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = true, errorMessage = null
                            )
                            Log.i(TAG, "Loading: " + result.message.toString())
                        }
                    }
                }
        }
    }
}