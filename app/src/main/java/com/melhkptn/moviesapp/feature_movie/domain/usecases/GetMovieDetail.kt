package com.melhkptn.moviesapp.feature_movie.domain.usecases

import com.melhkptn.moviesapp.core.util.Resource
import com.melhkptn.moviesapp.feature_movie.domain.model.Movie
import com.melhkptn.moviesapp.feature_movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetail @Inject constructor (
    private val movieRepository: MovieRepository
) {
    operator fun invoke(id: Int): Flow<Resource<Movie?>> {
        return movieRepository.getMovieDetail(id)
    }
}