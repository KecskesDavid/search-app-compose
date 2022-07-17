package com.example.searchappcompose.data.remote.service

import com.example.searchappcompose.data.remote.dto.news.NewsDataDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface WebSearchApi {

    @GET("search/NewsSearchAPI/")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int,
        @Query("autoCorrect") autoCorrect: Boolean = true,
        @Query("withThumbnails") withThumbnails: Boolean = true,
    ) : NewsDataDTO
}