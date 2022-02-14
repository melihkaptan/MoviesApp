package com.melhkptn.moviesapp.feature_movie.presentation.getMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melhkptn.moviesapp.core.util.Status
import com.melhkptn.moviesapp.feature_movie.domain.model.Movie
import com.melhkptn.moviesapp.feature_movie.domain.usecases.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
) : ViewModel() {

    private val _popularMoviesLiveData = MutableLiveData<List<Movie>>()
    val popularMoviesLiveData: LiveData<List<Movie>>
        get() = _popularMoviesLiveData


    fun getPopularMovies() {
       viewModelScope.launch {
           movieUseCases.getPopularMovies(1).collect { popularMovies ->
               if (popularMovies.status == Status.SUCCESS){
                   _popularMoviesLiveData.value = popularMovies.data!!
               }
           }
       }
    }
}