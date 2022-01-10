package com.melhkptn.moviesapp.feature_movie.data.datasource.remote

data class MovieResponse(
    val page: Int?,
    val results: List<MovieDto>?,
    val total_pages: Int?,
    val total_results: Int?
)