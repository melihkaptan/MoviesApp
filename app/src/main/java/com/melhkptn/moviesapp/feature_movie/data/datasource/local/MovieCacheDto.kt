package com.melhkptn.moviesapp.feature_movie.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieCacheDto(
    @PrimaryKey
    val id: Int,
    val title: String?,
    val overview: String?,
    val poster_path: String?,
    val vote_average: Double?
)