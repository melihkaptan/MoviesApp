package com.melhkptn.moviesapp.feature_movie.domain.di

import android.app.Application
import androidx.room.Room
import com.melhkptn.moviesapp.BuildConfig
import com.melhkptn.moviesapp.feature_movie.data.datasource.local.LocalDataSource
import com.melhkptn.moviesapp.feature_movie.data.datasource.local.MovieCacheMapper
import com.melhkptn.moviesapp.feature_movie.data.datasource.local.MovieDao
import com.melhkptn.moviesapp.feature_movie.data.datasource.local.MovieDatabase
import com.melhkptn.moviesapp.feature_movie.data.datasource.remote.MovieApi
import com.melhkptn.moviesapp.feature_movie.data.datasource.remote.MovieRemoteMapper
import com.melhkptn.moviesapp.feature_movie.data.datasource.remote.RemoteDataSource
import com.melhkptn.moviesapp.feature_movie.data.repository.MovieRepositoryImpl
import com.melhkptn.moviesapp.feature_movie.domain.repository.MovieRepository
import com.melhkptn.moviesapp.feature_movie.domain.usecases.GetMovieDetail
import com.melhkptn.moviesapp.feature_movie.domain.usecases.GetPopularMovies
import com.melhkptn.moviesapp.feature_movie.domain.usecases.MovieUseCases
import com.melhkptn.moviesapp.feature_movie.domain.util.Constants
import com.melhkptn.moviesapp.feature_movie.domain.util.Constants.BASE_URL
import com.melhkptn.moviesapp.feature_movie.domain.util.DefaultRequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(application: Application): MovieDatabase {
        return Room.databaseBuilder(
            application,
            MovieDatabase::class.java,
            "movies_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(database: MovieDatabase) : MovieDao = database.movieDao()

    @Provides
    @Singleton
    fun provideMovieRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        cacheMapper: MovieCacheMapper,
        remoteMapper: MovieRemoteMapper
    ): MovieRepository {
        return MovieRepositoryImpl(
            localDataSource, remoteDataSource, cacheMapper, remoteMapper
        )
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        requestInterceptor: DefaultRequestInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        with(OkHttpClient.Builder()) {
            addInterceptor(requestInterceptor)
            if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
            connectTimeout(Constants.TIMEOUT_MILIS, TimeUnit.MILLISECONDS)
            build()
        }


    @Provides
    @Singleton
    fun provideMovieApi(
        client: OkHttpClient
    ): MovieApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieUseCases(movieRepository: MovieRepository): MovieUseCases =
        MovieUseCases(
            getPopularMovies = GetPopularMovies(movieRepository),
            getMovieDetail = GetMovieDetail(movieRepository)
        )
}