package com.example.searchappcompose.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.searchappcompose.data.consts.Constants
import com.example.searchappcompose.data.local.dao.NewsDao
import com.example.searchappcompose.domain.model.news.NewsInfo

@Database(
    entities = [NewsInfo::class], version = Constants.DATABASE_VERSION
)
abstract class SearchAppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}