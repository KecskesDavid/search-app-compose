package com.example.searchappcompose.app.core.models

data class SearchCategory(
    val categoryName: String,
    var isSelected: Boolean = false,
    val iconId: Int? = null
) {
    companion object {
        fun getList() = mutableListOf(
            SearchCategory(categoryName = "Politics"),
            SearchCategory(categoryName = "Sport"),
            SearchCategory(categoryName = "Foreign"),
            SearchCategory(categoryName = "News"),
            SearchCategory(categoryName = "Business"),
            SearchCategory(categoryName = "Historical"),
        )
    }
}