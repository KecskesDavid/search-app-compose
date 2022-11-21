package com.example.searchappcompose.app.screens.main

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.searchappcompose.app.core.util.getRoute
import com.example.searchappcompose.app.screens.details.DetailViewModel
import com.example.searchappcompose.app.screens.navigation.BottomNavigationScreens
import com.example.searchappcompose.app.screens.navigation.BottomNavigationScreens.FavoriteScreen
import com.example.searchappcompose.app.screens.navigation.BottomNavigationScreens.SearchScreen
import com.example.searchappcompose.app.screens.navigation.Screen
import com.example.searchappcompose.app.screens.navigation.Screen.DetailScreen
import com.example.searchappcompose.app.screens.navigation.SearchAppBottomBar
import com.example.searchappcompose.app.screens.navigation.SearchAppNavHost
import com.example.searchappcompose.app.screens.search.SearchViewModel
import com.example.searchappcompose.app.theme.SearchAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { }

        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.INTERNET
            )
        )

        setContent {
            SearchApp()
        }
    }
}

@Composable
fun SearchApp() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // We want to get the screen so we can decide on the scaffold params
    val route = navBackStackEntry?.destination?.getRoute()

    // We can decide where we want to show/hide the top/bottom bar
    bottomBarState.value = when (route) {
        Screen.route_details -> false
        BottomNavigationScreens.route_favorites -> true
        BottomNavigationScreens.route_search -> true
        else -> false
    }

    SearchAppComposeTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = {
                if (bottomBarState.value) SearchAppBottomBar(navController)
            },
            modifier = Modifier.padding(0.dp)
        ) {
            SearchAppNavHost(
                navController,
                modifier = Modifier.padding(it)
            )
        }
    }
}

