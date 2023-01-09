package com.example.searchappcompose.app.core.models

enum class SearchCategory(
    val categoryName: String,
    var isSelected: Boolean = false,
    val iconId: Int? = null
) {
    Politics("Politics"),
    Sport("Sport"),
    Foreign("Foreign"),
    News("News"),
    Business("Business"),
    Historical("Historical ")
}