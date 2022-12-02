package com.example.searchappcompose.domain.use_case.add_to_favorites

import com.example.searchappcompose.domain.model.news.NewsInfo
import com.example.searchappcompose.domain.repository.NewsRepository
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(news: NewsInfo) {
        newsRepository.addToFavorites(news)
    }
}