package com.melhkptn.moviesapp.feature_movie.data.datasource.remote

import com.melhkptn.moviesapp.feature_movie.domain.model.Movie
import com.melhkptn.moviesapp.core.util.FromEntityMapper
import javax.inject.Inject

class MovieRemoteMapper @Inject constructor(
) : FromEntityMapper<MovieDto, Movie> {

    override fun mapFromEntity(entityModel: MovieDto) = Movie(
        id = entityModel.id,
        title = entityModel.title,
        overview = entityModel.overview,
        poster_path = "https://image.tmdb.org/t/p/w500/${entityModel.poster_path}",
        vote_average = entityModel.vote_average
    )
}