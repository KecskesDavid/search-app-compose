package com.example.searchappcompose.domain.repository

import com.example.searchappcompose.domain.model.news.News
import com.example.searchappcompose.domain.use_case.news.GetNewsUseCase

interface NewsRepository {

    suspend fun getNews(
        query: String,
        pageNumber: Int,
        pageSize: Int,
    ) : News
}