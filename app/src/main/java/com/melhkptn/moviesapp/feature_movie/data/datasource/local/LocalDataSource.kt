package com.melhkptn.moviesapp.feature_movie.data.datasource.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: MovieDao
) {
    suspend fun insertMovie(movieCacheDto: MovieCacheDto) = dao.insertMovie(movieCacheDto)

    fun gellAllMovies() : Flow<List<MovieCacheDto>?> = dao.getAllMoviesDb()

    fun getMovieDetail(id: Int) : Flow<MovieCacheDto?> = dao.getMovieDetailDb(id)
}