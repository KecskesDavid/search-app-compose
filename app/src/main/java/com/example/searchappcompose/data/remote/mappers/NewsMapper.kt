package com.example.searchappcompose.data.remote.mappers

import com.example.searchappcompose.data.remote.dto.news.NewsDataDTO
import com.example.searchappcompose.domain.model.news.News
import com.example.searchappcompose.domain.model.news.NewsInfo

fun NewsDataDTO.toNews(): News {
    return News(news.map {
        NewsInfo(
            it.id,
            it.title,
            it.url,
            it.description,
            it.body,
            it.snippet,
            it.datePublished,
            it.image.url,
            it.image.thumbnail,
        )
    }.toList())
}