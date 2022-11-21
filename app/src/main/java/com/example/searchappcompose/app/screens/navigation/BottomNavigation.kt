package com.example.searchappcompose.app.screens.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.searchappcompose.R

sealed class BottomNavigationScreens(
    route: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) : Screen(route) {
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
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        BottomNavigationScreens.screens.forEach { screen ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = { navController.navigate(screen.route) },
                label = {
                    Text(
                        text = stringResource(id = screen.label),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = stringResource(id = screen.label)
                    )
                },
                alwaysShowLabel = false,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
            )
        }
    }
}