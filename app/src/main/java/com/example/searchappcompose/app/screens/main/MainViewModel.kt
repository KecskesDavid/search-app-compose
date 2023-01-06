package com.example.searchappcompose.app.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    var isLoading by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            startLoading()
        }
    }

    // Simulating some background calls while showing the splash screen
    private suspend fun startLoading() {
        isLoading = true
        delay(3000)
        isLoading = false
    }
}