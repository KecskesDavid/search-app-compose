package com.example.searchappcompose.app.screens.main

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
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
import com.example.searchappcompose.app.screens.search.AppBarState
import com.example.searchappcompose.app.screens.search.SearchViewModel
import androidx.compose.material3.MaterialTheme as theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(searchViewModel: SearchViewModel) {

    searchViewModel.getNews()

    val appBarState by searchViewModel.appBarState
    val searchQuery by searchViewModel.searchQuery

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(theme.colorScheme.primary),
    ) {
        MainScreenMainAppBar(
            appBarState,
            searchQuery = searchQuery,
            onSearchIconClick = {
                searchViewModel.updateAppBarState(AppBarState.OPENED)
            },
            closeIconClick = {
                searchViewModel.updateAppBarState(AppBarState.CLOSED)
            }
        ) {
            searchViewModel.updateSearchQuery(it)
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
                    .fillMaxWidth(),
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
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = theme.colorScheme.secondary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = theme.colorScheme.primary
                ),
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