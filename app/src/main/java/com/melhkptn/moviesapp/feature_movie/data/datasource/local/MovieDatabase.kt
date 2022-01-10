package com.melhkptn.moviesapp.feature_movie.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieCacheDto::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase(){

    abstract fun movieDao() : MovieDao
}