package com.example.searchappcompose.app.screens.search

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.searchappcompose.R
import com.example.searchappcompose.app.core.models.SearchCategory
import com.example.searchappcompose.app.core.ui.NewsList
import com.example.searchappcompose.app.core.ui.background.AppBackground
import com.example.searchappcompose.app.core.ui.chip_list.CategoryList
import com.example.searchappcompose.app.core.ui.loading_overlay.LoadingOverlay
import com.example.searchappcompose.app.screens.navigation.Screen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(), navController: NavHostController
) {

    val state = searchViewModel.state

    AppBackground {
        MainScreenMainAppBar(state.appBarState,
            searchQuery = state.searchQuery,
            onSearchIconClick = {
                searchViewModel.onEvent(SearchEvent.OnAppBarStateChange(AppBarState.OPENED))
            },
            closeIconClick = {
                searchViewModel.onEvent(SearchEvent.OnAppBarStateChange(AppBarState.CLOSED))
            },
            onQueryChange = {
                searchViewModel.onEvent(SearchEvent.OnQueryEntered(it))
            })

        Column(modifier = Modifier.fillMaxSize()) {
            CategoryList(content = getDummyData(), {})

            if (state.isLoading) {
                LoadingOverlay()
            } else {
                NewsList(
                    news = state.news,
                    onNewsClick = { URL ->
                        navController.navigate(
                            Screen.DetailScreen.withArguments(
                                URLEncoder.encode(
                                    URL, StandardCharsets.UTF_8.toString(),
                                ),
                            ),
                        )
                    },
                    onFavoritesIconClick = {
                        searchViewModel.onEvent(
                            SearchEvent.OnFavoritesClicked(it),
                        )
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreenMainAppBar(
    appBarState: AppBarState,
    searchQuery: String,
    onSearchIconClick: () -> Unit,
    closeIconClick: () -> Unit,
    onQueryChange: (String) -> Unit
) {
    AnimatedContent(targetState = appBarState, transitionSpec = {
        when (targetState) {
            AppBarState.OPENED -> {
                slideInHorizontally { height -> height } + fadeIn() with slideOutHorizontally { height -> -height } + fadeOut()
            }

            AppBarState.CLOSED -> {
                slideInHorizontally { height -> -height } + fadeIn() with slideOutHorizontally { height -> height } + fadeOut()
            }
        }
    }) {
        when (appBarState) {
            AppBarState.OPENED -> {
                MainScreenTopSearchBar(
                    text = searchQuery,
                    onQueryChange = onQueryChange,
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
                    imageVector = Icons.Filled.Search, contentDescription = "SearchButton"
                )
            }
        },
    )
}

@Composable
fun MainScreenTopSearchBar(
    text: String, onQueryChange: (String) -> Unit, closeIconClick: () -> Unit
) {
    SmallTopAppBar(title = {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { onQueryChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium), text = "Search.."
                )
            },
            textStyle = TextStyle(
                fontSize = 17.sp
            ),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colorScheme.secondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = MaterialTheme.colorScheme.primary
            ),
        )
    }, navigationIcon = {
        IconButton(onClick = { onQueryChange(text) }) {
            Icon(
                Icons.Filled.Search, "SearchButton"
            )
        }
    }, actions = {
        // RowScope here, so these icons will be placed horizontally
        IconButton(onClick = { closeIconClick() }) {
            Icon(Icons.Filled.Close, contentDescription = "Localized description")
        }
    }

    )
}

private fun getDummyData() = mutableListOf(
    SearchCategory("Politics", iconId = null),
    SearchCategory("Sport", iconId = null),
    SearchCategory("News", iconId = null, isSelected = true),
    SearchCategory("Other", iconId = null),
)