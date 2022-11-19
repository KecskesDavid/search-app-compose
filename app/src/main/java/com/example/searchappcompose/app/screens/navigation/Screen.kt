package com.example.searchappcompose.app.screens.navigation

sealed class Screen(val route: String) {
    object DetailScreen : Screen("detail_screen")

    fun withArguments(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}