package com.example.searchappcompose.app.core.util

import androidx.navigation.NavDestination
import com.example.searchappcompose.app.screens.navigation.BottomNavigationScreens
import com.example.searchappcompose.app.screens.navigation.Screen

// Since nav destination can have arguments we need to find the screen name in the route
fun NavDestination.getRoute(): String {
    return when {
        this.route?.contains(Screen.route_details) == true -> Screen.route_details
        this.route?.contains(BottomNavigationScreens.route_search) == true -> BottomNavigationScreens.route_search
        this.route?.contains(BottomNavigationScreens.route_favorites) == true -> BottomNavigationScreens.route_favorites
        else -> { throw Exception() }
    }
}