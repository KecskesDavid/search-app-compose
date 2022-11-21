package com.example.searchappcompose.app.screens.search

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.searchappcompose.R
import com.example.searchappcompose.app.core.models.SearchCategory
import com.example.searchappcompose.app.core.ui.chip_list.CategoryList
import com.example.searchappcompose.app.core.ui.item_divider.NewsListDivider
import com.example.searchappcompose.app.core.ui.loading_overlay.LoadingOverlay
import com.example.searchappcompose.app.core.util.DateUtil
import com.example.searchappcompose.app.screens.navigation.Screen
import com.example.searchappcompose.domain.model.news.NewsInfo
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun SearchScreen(searchViewModel: SearchViewModel = hiltViewModel(), navController: NavHostController) {

    val state = searchViewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
    ) {
        MainScreenMainAppBar(
            state.appBarState,
            searchQuery = state.searchQuery,
            onSearchIconClick = {
                searchViewModel.onEvent(SearchEvent.OnAppBarStateChange(AppBarState.OPENED))
            },
            closeIconClick = {
                searchViewModel.onEvent(SearchEvent.OnAppBarStateChange(AppBarState.CLOSED))
            },
            onValueChange = {
                searchViewModel.onEvent(SearchEvent.OnQueryEntered(it))
            }
        )

        Column(modifier = Modifier.fillMaxSize()) {
            if (state.isLoading) {
                LoadingOverlay()
            }

            CategoryList(content = getDummyData(), {})

            state.news?.let { news ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(8.dp, 0.dp)
                ) {
                    items(news.size) { index ->
                        NewsCard(
                            newsInfo = news[index],
                            onClick = {
                                navController.navigate(
                                    Screen.DetailScreen.withArguments(
                                        URLEncoder.encode(
                                            news[index].url,
                                            StandardCharsets.UTF_8.toString()
                                        )
                                    )
                                )
                            },
                        )
                        NewsListDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun NewsCard(
    newsInfo: NewsInfo,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(90.dp)
            .clickable { onClick.invoke() }
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = newsInfo.imageUrl,
            contentDescription = null,
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.placeholder),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .width(82.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = DateUtil.toNewsCardTimestamp(newsInfo.datePublished),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 10.sp,
                modifier = Modifier
                    .alpha(alpha = 0.3f)
                    .padding(top = 4.dp),
            )
            Text(
                text = newsInfo.title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                modifier = Modifier.padding(top = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
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