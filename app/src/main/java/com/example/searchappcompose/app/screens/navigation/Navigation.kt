package com.example.searchappcompose.app.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.searchappcompose.app.screens.favorites.FavoritesScreen
import com.example.searchappcompose.app.screens.main.SearchScreen
import com.example.searchappcompose.app.screens.search.SearchViewModel

@Composable
fun SearchAppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.route_search
    ) {
        composable(BottomNavigationScreens.route_search) {
            SearchScreen(SearchViewModel())
        }
        composable(BottomNavigationScreens.route_favorites) {
            FavoritesScreen()
        }
    }
}