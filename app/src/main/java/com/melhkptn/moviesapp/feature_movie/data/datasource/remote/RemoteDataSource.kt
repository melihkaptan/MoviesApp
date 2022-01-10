package com.melhkptn.moviesapp.feature_movie.data.datasource.remote

import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: MovieApi
) {
    suspend fun getAllMovies(page : Int) : Response<MovieResponse> = api.getAllMovies(page = page)

    suspend fun getMovieDetail(movieId: Int) : Response<MovieDto> = api.getMovieDetail(movieId = movieId)
}