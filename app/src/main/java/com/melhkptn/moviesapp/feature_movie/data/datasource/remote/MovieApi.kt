package com.melhkptn.moviesapp.feature_movie.data.datasource.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi{

    @GET("movie/popular")
    suspend fun getAllMovies(
        @Path("page") page: Int
    ) : Response<MovieResponse>

    @GET("/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId : Int
    ) : Response<MovieDto>
}