package com.example.searchappcompose.app.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.searchappcompose.app.screens.navigation.SearchAppBottomBar
import com.example.searchappcompose.app.screens.navigation.SearchAppNavHost
import com.example.searchappcompose.app.screens.search.SearchViewModel
import com.example.searchappcompose.app.theme.SearchAppComposeTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchApp()
        }
    }
}

@Composable
fun SearchApp() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    SearchAppComposeTheme() {
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = { SearchAppBottomBar(navController) }
        ) {
            SearchAppNavHost(
                navController
            )
        }
    }
}

