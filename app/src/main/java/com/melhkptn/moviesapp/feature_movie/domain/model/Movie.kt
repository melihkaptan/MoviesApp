package com.melhkptn.moviesapp.feature_movie.domain.model

data class Movie(
    val id: Int,
    val title: String?,
    val overview: String?,
    val poster_path: String?,
    val vote_average: Double?
)