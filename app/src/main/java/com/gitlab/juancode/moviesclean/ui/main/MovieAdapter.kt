package com.gitlab.juancode.moviesclean.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gitlab.juancode.domain.Movie
import com.gitlab.juancode.moviesclean.R
import com.gitlab.juancode.moviesclean.databinding.ItemMovieBinding
import com.gitlab.juancode.moviesclean.ui.common.loadImage
import kotlin.properties.Delegates

@SuppressLint("NotifyDataSetChanged")
class MovieAdapter(private val listener: (Movie) -> Unit): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var movies: List<Movie> by Delegates.observable(emptyList()) {_,_,_ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            listener(movie)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                titleMovie.text = movie.title
                avatarImage.loadImage("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
            }
        }
    }
}