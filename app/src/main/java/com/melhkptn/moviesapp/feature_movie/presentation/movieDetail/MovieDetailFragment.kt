package com.melhkptn.moviesapp.feature_movie.presentation.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.melhkptn.moviesapp.core.util.loadImage
import com.melhkptn.moviesapp.databinding.MovieDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val viewModel: MovieDetailViewModel by viewModels()
    private var _binding: MovieDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.movieLiveData.observe(viewLifecycleOwner, {
            if (it != null){
                it.poster_path?.let { path ->
                    binding.ivPosterDetail.loadImage(
                        path,requireContext()
                    )
                }
                binding.tvTitle.text = it.title
                binding.tvVote.text = it.vote_average.toString()
                binding.tvDescription.text = it.overview
                //TODO:Add Custom Chips for Genres
                //binding.chipGroup.addView()
                binding.ivBack.setOnClickListener {
                    findNavController().navigateUp()
                }
            }
        })
    }

    companion object {
        fun newInstance() = MovieDetailFragment()
    }

}