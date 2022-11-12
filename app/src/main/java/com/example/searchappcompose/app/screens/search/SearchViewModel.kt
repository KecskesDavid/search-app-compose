package com.example.searchappcompose.app.screens.search

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchappcompose.domain.model.news.News
import com.example.searchappcompose.domain.use_case.news.GetNewsUseCase
import com.example.searchappcompose.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    var state by mutableStateOf(SearchScreenState())
        private set

    private var _appBarState: MutableState<AppBarState> =
        mutableStateOf(value = AppBarState.CLOSED)
    val appBarState: State<AppBarState> = _appBarState

    private var _searchQuery: MutableState<String> =
        mutableStateOf(value = "")
    val searchQuery: State<String> = _searchQuery


    fun updateAppBarState(state: AppBarState) {
        _appBarState.value = state
    }

    fun updateSearchQuery(searchQuery: String) {
        _searchQuery.value = searchQuery
    }

    fun getNews() {
        viewModelScope.launch {
            getNewsUseCase("taylor swift", 1, 10)
                .collect { result ->
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
                                news = null,
                                isLoading = false,
                                errorMessage = result.message
                            )
                            Log.i(TAG, "Error: " + result.message.toString())
                        }
                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = true,
                                errorMessage = null
                            )
                            Log.i(TAG, "Loading: " + result.message.toString())
                        }
                    }
                }
        }
    }

    companion object {
        const val TAG = "SearchViewModel"
    }
}