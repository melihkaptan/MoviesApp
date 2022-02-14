package com.melhkptn.moviesapp.feature_movie.presentation.getMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.melhkptn.moviesapp.databinding.MovieListFragmentBinding
import com.melhkptn.moviesapp.feature_movie.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private val viewModel: MovieListViewModel by viewModels()

    private var _binding: MovieListFragmentBinding? = null
    private val binding get() = _binding!!
    private var adapter = MovieListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MovieListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        viewModel.getPopularMovies()
        viewModel.popularMoviesLiveData.observe(viewLifecycleOwner,  {
            if (it.isNotEmpty())
                adapter.addMovieList(it as ArrayList<Movie>)
        })
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }
}