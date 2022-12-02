package com.example.searchappcompose.domain.repository

import com.example.searchappcompose.domain.model.news.News
import com.example.searchappcompose.domain.model.news.NewsInfo
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(
        query: String,
        pageNumber: Int,
        pageSize: Int,
    ) : News

    fun getFavorites(): Flow<List<News>>

    suspend fun addToFavorites(newsInfo: NewsInfo)

    suspend fun deleteFromFavorites(newsInfo: NewsInfo)
}