package com.example.searchappcompose.app.screens.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchappcompose.domain.model.news.News
import com.example.searchappcompose.domain.use_case.news.GetNewsUseCase
import com.example.searchappcompose.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

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
                .collect() { result ->
                    when (result) {
                        is Resource.Success -> {
                            Log.i(TAG, (result.data as News).news.size.toString())
                        }
                        is Resource.Error -> {
                            Log.i(TAG, "Error:" + result.message)
                        }
                        is Resource.Loading -> {
                            Log.i(TAG, "Loading")
                        }
                    }
                }
        }
    }

    companion object {
        const val TAG = "SearchViewModel"
    }
}