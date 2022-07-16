package com.example.searchappcompose.data.remote.repository

import com.example.searchappcompose.data.remote.mappers.toNews
import com.example.searchappcompose.data.remote.service.WebSearchApi
import com.example.searchappcompose.domain.model.news.News
import com.example.searchappcompose.domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val webSearchApi: WebSearchApi
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
}