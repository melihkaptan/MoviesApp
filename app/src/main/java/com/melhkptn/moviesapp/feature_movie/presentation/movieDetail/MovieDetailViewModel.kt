package com.melhkptn.moviesapp.feature_movie.presentation.movieDetail

import androidx.lifecycle.*
import com.melhkptn.moviesapp.core.util.Status
import com.melhkptn.moviesapp.feature_movie.domain.model.Movie
import com.melhkptn.moviesapp.feature_movie.domain.usecases.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private var movieUseCases: MovieUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var currentMovieId: Int? = null

    private val _movieLiveData = MutableLiveData<Movie>()
    val movieLiveData: LiveData<Movie>
        get() = _movieLiveData

    init {
        savedStateHandle.get<Int>("id")?.let { id ->
            if (id != -1) {
                viewModelScope.launch {
                    movieUseCases.getMovieDetail.invoke(id).collect { movie ->
                        if (movie.status == Status.SUCCESS){
                            _movieLiveData.value = movie.data!!
                        }
                    }
                    currentMovieId = _movieLiveData.value?.id
                }
            }
        }
    }
}