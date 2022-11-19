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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.searchappcompose.app.screens.navigation.SearchAppBottomBar
import com.example.searchappcompose.app.screens.navigation.SearchAppNavHost
import com.example.searchappcompose.app.screens.search.SearchViewModel
import com.example.searchappcompose.app.theme.SearchAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            // searchViewModel.getNews(searchQuery)
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.INTERNET
            )
        )
        setContent {
            SearchApp(
                searchViewModel
            )
        }
    }
}

@Composable
fun SearchApp(searchViewModel: SearchViewModel) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    SearchAppComposeTheme() {
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = { SearchAppBottomBar(navController) },
            modifier = Modifier.padding(0.dp)
        ) {
            SearchAppNavHost(
                navController,
                searchViewModel,
                modifier = Modifier.padding(it)
            )
        }
    }
}

