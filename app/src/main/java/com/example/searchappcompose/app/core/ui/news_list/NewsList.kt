package com.example.searchappcompose.app.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.searchappcompose.app.core.ui.item_divider.NewsListDivider
import com.example.searchappcompose.domain.model.news.NewsInfo

@Composable
fun NewsList(
    news: List<NewsInfo>?, onNewsClick: (String) -> Unit, onFavoritesIconClick: (NewsInfo) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        news?.let { newsList ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp, 0.dp)
            ) {
                items(newsList.size) { index ->
                    val newsInfo = newsList[index]

                    NewsCard(
                        newsInfo = newsInfo,
                        onNewsClick = onNewsClick,
                        onFavoritesIconClick = onFavoritesIconClick
                    )
                    NewsListDivider()
                }
            }
        }
    }
}