package com.example.searchappcompose.app.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.searchappcompose.app.screens.favorites.FavoritesScreen
import com.example.searchappcompose.app.screens.search.SearchScreen
import com.example.searchappcompose.app.screens.search.SearchViewModel

@Composable
fun SearchAppNavHost(
    navController: NavHostController,
    searchViewModel: SearchViewModel,
    modifier: Modifier? = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.route_search
    ) {
        composable(BottomNavigationScreens.route_search) {
            SearchScreen(searchViewModel)
        }
        composable(BottomNavigationScreens.route_favorites) {
            FavoritesScreen()
        }
    }
}