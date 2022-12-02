package com.example.searchappcompose.data.repository

import com.example.searchappcompose.data.local.dao.NewsDao
import com.example.searchappcompose.data.local.database.SearchAppDatabase
import com.example.searchappcompose.data.mappers.toNews
import com.example.searchappcompose.data.remote.service.WebSearchApi
import com.example.searchappcompose.domain.model.news.News
import com.example.searchappcompose.domain.model.news.NewsInfo
import com.example.searchappcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import java.security.PrivateKey
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val webSearchApi: WebSearchApi,
    private val newsDao: NewsDao
) : NewsRepository {

    override suspend fun getNews(
        query: String,
        pageNumber: Int,
        pageSize: Int
    ): News {
        return webSearchApi.getNews(
            query,
            pageNumber,
            pageSize
        ).toNews()
    }

    override fun getFavorites(): Flow<List<News>> {
        TODO("Not yet implemented")
    }

    override suspend fun addToFavorites(newsInfo: NewsInfo) {
        newsDao.addToFavorites(newsInfo)
    }

    override suspend fun deleteFromFavorites(newsInfo: NewsInfo) {
        newsDao.deleteFromFavorites(newsInfo)
    }
}