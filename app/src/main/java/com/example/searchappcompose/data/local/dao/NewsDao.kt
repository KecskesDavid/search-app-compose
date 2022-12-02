package com.example.searchappcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.searchappcompose.domain.model.news.NewsInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM News")
    fun getFavorites(): Flow<List<NewsInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(newsInfo: NewsInfo)

    @Delete
    suspend fun deleteFromFavorites(newsInfo: NewsInfo)
}