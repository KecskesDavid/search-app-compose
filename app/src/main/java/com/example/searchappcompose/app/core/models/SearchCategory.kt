package com.example.searchappcompose.app.core.models

data class SearchCategory(
    val categoryName: String,
    val isSelected: Boolean = false,
    val iconId: Int?
)