package com.example.searchappcompose.app.model

data class SearchCategory(
    val categoryName: String,
    val isSelected: Boolean = false,
    val iconId: Int?
) {
}