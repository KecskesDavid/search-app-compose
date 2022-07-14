package com.example.searchappcompose.app.main

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.example.searchappcompose.R
import com.example.searchappcompose.app.chip_list.CategoryList
import com.example.searchappcompose.app.model.SearchCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(mainViewModel: MainViewModel) {

    val appBarState by mainViewModel.appBarState
    val searchQuery by mainViewModel.searchQuery

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MainScreenMainAppBar(
            appBarState,
            searchQuery = searchQuery,
            onSearchIconClick = {
                mainViewModel.updateAppBarState(AppBarState.OPENED)
            },
            closeIconClick = {
                mainViewModel.updateAppBarState(AppBarState.CLOSED)
            }
        ) {
            mainViewModel.updateSearchQuery(it)
        }

        CategoryList(content = getDummyData(), {})
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreenMainAppBar(
    appBarState: AppBarState,
    searchQuery: String,
    onSearchIconClick: () -> Unit,
    closeIconClick: () -> Unit,
    onValueChange: (String) -> Unit
) {
    AnimatedContent(
        targetState = appBarState,
        transitionSpec = {
            when (targetState) {
                AppBarState.OPENED -> {
                    slideInHorizontally { height -> height } + fadeIn() with
                            slideOutHorizontally { height -> -height } + fadeOut()
                }
                AppBarState.CLOSED -> {
                    slideInHorizontally { height -> -height } + fadeIn() with
                            slideOutHorizontally { height -> height } + fadeOut()
                }
            }
        }
    ) {
        when (appBarState) {
            AppBarState.OPENED -> {
                MainScreenTopSearchBar(
                    text = searchQuery,
                    onValueChange = onValueChange,
                    closeIconClick = closeIconClick
                )
            }
            AppBarState.CLOSED -> {
                MainScreenTopAppBar(
                    onSearchIconClick = onSearchIconClick
                )
            }
        }
    }
}

@Composable
fun MainScreenTopAppBar(onSearchIconClick: () -> Unit) {
    SmallTopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        actions = {
            IconButton(onClick = {
                onSearchIconClick()
            }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "SearchButton"
                )
            }
        },
    )
}

@Composable
fun MainScreenTopSearchBar(
    text: String,
    onValueChange: (String) -> Unit,
    closeIconClick: () -> Unit
) {
    SmallTopAppBar(
        title = {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                value = text,
                onValueChange = { onValueChange(it) },
                placeholder = {
                    Text(
                        modifier = Modifier.alpha(ContentAlpha.medium),
                        text = "Search.."
                    )
                },
                textStyle = TextStyle(
                    fontSize = MaterialTheme.typography.subtitle1.fontSize
                ),
                singleLine = true
            )
        },
        navigationIcon = {
            IconButton(onClick = { onSearchClick() }) {
                Icon(
                    Icons.Filled.Search,
                    "SearchButton"
                )
            }
        },
        actions = {
            // RowScope here, so these icons will be placed horizontally
            IconButton(onClick = { closeIconClick() }) {
                Icon(Icons.Filled.Close, contentDescription = "Localized description")
            }
        }

    )
}

private fun onSearchClick() {

}

private fun getDummyData() = mutableListOf(
    SearchCategory("Politics", iconId = null),
    SearchCategory("Sport", iconId = null),
    SearchCategory("News", iconId = null, isSelected = true),
    SearchCategory("Other", iconId = null),
)