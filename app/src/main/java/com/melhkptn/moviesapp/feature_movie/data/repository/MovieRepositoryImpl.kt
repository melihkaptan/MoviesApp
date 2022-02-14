package com.melhkptn.moviesapp.feature_movie.data.repository

import com.melhkptn.moviesapp.core.util.Resource
import com.melhkptn.moviesapp.feature_movie.data.datasource.local.LocalDataSource
import com.melhkptn.moviesapp.feature_movie.data.datasource.local.MovieCacheMapper
import com.melhkptn.moviesapp.feature_movie.data.datasource.remote.MovieRemoteMapper
import com.melhkptn.moviesapp.feature_movie.data.datasource.remote.RemoteDataSource
import com.melhkptn.moviesapp.feature_movie.domain.model.Movie
import com.melhkptn.moviesapp.feature_movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val cacheMapper: MovieCacheMapper,
    private val remoteMapper: MovieRemoteMapper
) : MovieRepository {

    override suspend fun insertMovies(lstMovie: List<Movie>) {
        lstMovie.map {
            localDataSource.insertMovie(cacheMapper.mapToEntity(it))
        }
    }

    override fun getAllMovies(page: Int): Flow<Resource<List<Movie>?>> = flow {
        emit(Resource.loading())
        val response = remoteDataSource.getAllMovies(page)
        if (response.isSuccessful) {
            response.body()?.let { popularMovieResponse ->
                popularMovieResponse.results?.let { results ->
                    val movies = results.map { movieDto ->
                        remoteMapper.mapFromEntity(movieDto)
                    }
                    insertMovies(movies)
                    emit(Resource.success(movies))
                } ?: getAllMoviesDb().collect {
                    emit(it)
                }
            } ?: getAllMoviesDb().collect {
                emit(it)
            }
        } else {
            getAllMoviesDb().collect {
                emit(it)
            }
        }
    }

    override fun getMovieDetail(id: Int): Flow<Resource<Movie?>> = flow {
        emit(Resource.loading())
        val response = remoteDataSource.getMovieDetail(id)
        if (response.isSuccessful){
            response.body()?.let {
                val movie = remoteMapper.mapFromEntity(it)
                emit(Resource.success(movie))
            } ?: getMovieDetailDb(id).collect {
                emit(it)
            }
        }
    }

    override fun getAllMoviesDb(): Flow<Resource<List<Movie>?>> = flow {
        emit(Resource.loading())
        localDataSource.gellAllMovies().collect { listMovieDb ->
            if (!listMovieDb.isNullOrEmpty()) {
                val movies = listMovieDb.map {
                    cacheMapper.mapFromEntity(it)
                }
                emit(Resource.success(movies))
            } else {
                emit(Resource.error("movies not found", null))
            }
        }
    }

    override fun getMovieDetailDb(id: Int): Flow<Resource<Movie?>> = flow {
        emit(Resource.loading())
        localDataSource.getMovieDetail(id).collect {
            it?.let {
                val movie = cacheMapper.mapFromEntity(it)
                Resource.success(movie)
            } ?: emit(Resource.error("movie detail not found", null))
        }
    }
}