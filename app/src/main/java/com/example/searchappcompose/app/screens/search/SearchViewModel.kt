package com.example.searchappcompose.app.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

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
}