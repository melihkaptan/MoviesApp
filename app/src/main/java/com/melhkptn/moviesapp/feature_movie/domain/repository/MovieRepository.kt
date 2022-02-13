package com.melhkptn.moviesapp.feature_movie.domain.repository

import com.melhkptn.moviesapp.feature_movie.domain.model.Movie
import com.melhkptn.moviesapp.core.util.Resource
import kotlinx.coroutines.flow.Flow


interface MovieRepository {

    suspend fun insertMovies(lstMovie: List<Movie>)
    fun getAllMovies(page: Int): Flow<Resource<List<Movie>?>>
    fun getMovieDetail(id: Int): Flow<Resource<Movie?>>
    fun getAllMoviesDb(): Flow<Resource<List<Movie>?>>
    fun getMovieDetailDb(id: Int): Flow<Resource<Movie?>>
}