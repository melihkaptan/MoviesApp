package com.melhkptn.moviesapp.feature_movie.data.datasource.remote

import com.melhkptn.moviesapp.BuildConfig.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getAllMovies(
        @Query("page") page: Int? = null,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieDto>
}