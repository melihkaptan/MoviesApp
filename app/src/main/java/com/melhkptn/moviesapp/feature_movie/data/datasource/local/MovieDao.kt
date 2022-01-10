package com.melhkptn.moviesapp.feature_movie.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieCacheDto: MovieCacheDto)

    @Query("SELECT * FROM movies")
    fun getAllMoviesDb() : Flow<List<MovieCacheDto>>

    @Query("SELECT * FROM movies WHERE id =:id")
    fun getMovieDetailDb(id : Int) : Flow<MovieCacheDto>
}