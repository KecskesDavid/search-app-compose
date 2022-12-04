package com.example.searchappcompose.domain.use_case.get_favorites

import com.example.searchappcompose.domain.model.news.NewsInfo
import com.example.searchappcompose.domain.repository.NewsRepository
import com.example.searchappcompose.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(): Flow<Resource<List<NewsInfo>>> {
        return flow {
            emit(Resource.Loading())

            val news = newsRepository.getFavorites()

            emit(Resource.Success(news))
        }.catch { exception ->
            emit(Resource.Error(message = exception.message ?: "Something went wrong"))
        }
    }
}