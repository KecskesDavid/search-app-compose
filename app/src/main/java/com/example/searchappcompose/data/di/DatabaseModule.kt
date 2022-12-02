package com.example.searchappcompose.data.di

import android.content.Context
import androidx.room.Room
import com.example.searchappcompose.data.consts.Constants
import com.example.searchappcompose.data.local.dao.NewsDao
import com.example.searchappcompose.data.local.database.SearchAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): SearchAppDatabase {
        return Room.databaseBuilder(
            context,
            SearchAppDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(database: SearchAppDatabase): NewsDao {
        return database.newsDao()
    }

}