package com.example.searchappcompose.data.di

import com.example.searchappcompose.data.repository.FakeNewsRepositoryImpl
import com.example.searchappcompose.data.repository.NewsRepositoryImpl
import com.example.searchappcompose.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
        // fakeNewsRepositoryImpl: FakeNewsRepositoryImpl
    ): NewsRepository
}