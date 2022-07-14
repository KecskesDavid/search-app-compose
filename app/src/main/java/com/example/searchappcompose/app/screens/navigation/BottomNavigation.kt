package com.example.searchappcompose.app.screens.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.searchappcompose.R

sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) {
    companion object {
        val screens = listOf(
            SearchScreen,
            FavoriteScreen
        )

        const val route_search = "search"
        const val route_favorites = "favorites"
    }

    object SearchScreen : BottomNavigationScreens(
        route_search,
        R.string.search,
        R.drawable.ic_search
    )

    object FavoriteScreen : BottomNavigationScreens(
        route_favorites,
        R.string.favorites,
        R.drawable.ic_favorites
    )
}

@Composable
fun SearchAppBottomBar(navController: NavHostController) {
    BottomNavigation {
        BottomNavigationScreens.screens.forEach { screen ->
            BottomNavigationItem(
                selected = navController.currentDestination?.route?.equals(screen.route) ?: false,
                onClick = { navController.navigate(screen.route) },
                label = {
                    Text(text = stringResource(id = screen.label))
                },
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = stringResource(id = screen.label)
                    )
                },
                alwaysShowLabel = false
            )
        }
    }
}