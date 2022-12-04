package com.example.searchappcompose.domain.repository

import com.example.searchappcompose.domain.model.news.News
import com.example.searchappcompose.domain.model.news.NewsInfo
import com.example.searchappcompose.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(
        query: String,
        pageNumber: Int,
        pageSize: Int,
    ) : News

    suspend fun getFavorites(): List<NewsInfo>

    suspend fun addToFavorites(newsInfo: NewsInfo)

    suspend fun deleteFromFavorites(newsInfo: NewsInfo)
}