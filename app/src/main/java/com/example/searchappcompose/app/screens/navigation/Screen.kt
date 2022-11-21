package com.example.searchappcompose.app.screens.navigation

sealed class Screen(val route: String) {
    companion object {
        val screens = listOf(
            DetailScreen
        )

        const val route_details = "detail"
    }

    object DetailScreen : Screen(route_details)

    fun withArguments(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}