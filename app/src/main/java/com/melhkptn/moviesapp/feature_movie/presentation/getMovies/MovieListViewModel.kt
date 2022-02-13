package com.melhkptn.moviesapp.feature_movie.presentation.getMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melhkptn.moviesapp.core.util.Status
import com.melhkptn.moviesapp.feature_movie.domain.model.Movie
import com.melhkptn.moviesapp.feature_movie.domain.usecases.GetPopularMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMovies
) : ViewModel() {

    private val _popularMoviesLiveData = MutableLiveData<List<Movie>>()
    val popularMoviesLiveData: LiveData<List<Movie>>
        get() = _popularMoviesLiveData

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
       viewModelScope.launch {
           getPopularMoviesUseCase(1).collect { popularMovies ->
               if (popularMovies.status == Status.SUCCESS){
                   _popularMoviesLiveData.value = popularMovies.data!!
               }
           }
       }
    }
}