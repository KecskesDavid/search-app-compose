package com.example.searchappcompose.app.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.searchappcompose.app.screens.details.DetailsScreen
import com.example.searchappcompose.app.screens.details.DetailViewModel
import com.example.searchappcompose.app.screens.favorites.FavoritesScreen
import com.example.searchappcompose.app.screens.search.SearchScreen
import com.example.searchappcompose.app.screens.search.SearchViewModel

@Composable
fun SearchAppNavHost(
    navController: NavHostController,
    modifier: Modifier? = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.route_search
    ) {
        composable(BottomNavigationScreens.route_search) {
            SearchScreen(navController = navController)
        }
        composable(BottomNavigationScreens.route_favorites) {
            FavoritesScreen(navController = navController)
        }
        composable(
            route = Screen.DetailScreen.route + "/{webUrl}",
            arguments = listOf(
                navArgument("webUrl") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )) { entry ->
            DetailsScreen(webUrl = entry.arguments?.getString("webUrl"))
        }
    }
}