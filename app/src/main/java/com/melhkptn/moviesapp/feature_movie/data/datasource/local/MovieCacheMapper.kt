package com.melhkptn.moviesapp.feature_movie.data.datasource.local

import com.melhkptn.moviesapp.feature_movie.domain.model.Movie
import com.melhkptn.moviesapp.core.util.EntityMapper
import javax.inject.Inject

class MovieCacheMapper @Inject constructor(

): EntityMapper<MovieCacheDto, Movie> {

    override fun mapFromEntity(entityModel: MovieCacheDto)= Movie (
        id = entityModel.id,
        title = entityModel.title,
        overview = entityModel.overview,
        poster_path = entityModel.poster_path,
        vote_average = entityModel.vote_average
    )

    override fun mapToEntity(domainModel: Movie)= MovieCacheDto(
        id = domainModel.id,
        title = domainModel.title,
        overview = domainModel.overview,
        poster_path = domainModel.poster_path,
        vote_average = domainModel.vote_average
    )
}