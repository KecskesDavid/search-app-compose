package com.example.searchappcompose.domain.use_case.delete_from_favorites

import com.example.searchappcompose.domain.model.news.NewsInfo
import com.example.searchappcompose.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteFromFavoritesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(news: NewsInfo) {
        newsRepository.deleteFromFavorites(news)
    }
}