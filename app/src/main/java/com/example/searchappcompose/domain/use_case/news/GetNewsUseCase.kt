package com.example.searchappcompose.domain.use_case.news

import com.example.searchappcompose.domain.repository.NewsRepository
import com.example.searchappcompose.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(query: String, pageNumber: Int, pageSize: Int): Flow<Resource<*>> {
        return flow {
            emit(Resource.Loading())
            val news = newsRepository.getNews(query, pageNumber, pageSize)

            emit(Resource.Success(news))
        }.onStart {
            emit(Resource.Loading())
        }.catch { exception ->
            emit(Resource.Error(message = exception.message ?: "Something went wrong"))
        }
    }
}