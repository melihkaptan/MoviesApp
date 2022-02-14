package com.melhkptn.moviesapp.feature_movie.presentation.getMovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.melhkptn.moviesapp.R
import com.melhkptn.moviesapp.core.util.loadImage
import com.melhkptn.moviesapp.feature_movie.domain.model.Movie
import kotlinx.android.synthetic.main.movie_list_item.view.*


class MovieListAdapter(
    private var movieList: ArrayList<Movie>
) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movie_list_item, parent, false)
        return MovieListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        movieList[position].poster_path?.let { path ->
            holder.itemView.ivPoster.loadImage(path, holder.itemView.context)
        }
        holder.itemView.tvRate.text = movieList[position].vote_average.toString()
        holder.itemView.setOnClickListener {
            val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movieList[position].id)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = movieList.size

    fun addMovieList(list: ArrayList<Movie>) {
        this.movieList = list
        notifyDataSetChanged()
    }
}