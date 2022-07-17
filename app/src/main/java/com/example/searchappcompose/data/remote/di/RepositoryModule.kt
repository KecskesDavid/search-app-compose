package com.example.searchappcompose.data.remote.di

import com.example.searchappcompose.data.remote.repository.NewsRepositoryImpl
import com.example.searchappcompose.data.remote.service.WebSearchApi
import com.example.searchappcompose.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(webSearchApi: WebSearchApi): NewsRepository {
        return NewsRepositoryImpl(webSearchApi)
    }
}