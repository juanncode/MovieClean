package com.gitlab.juancode.moviesclean.ui.search

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gitlab.juancode.domain.Movie
import com.gitlab.juancode.moviesclean.databinding.ItemSearchBinding
import com.gitlab.juancode.moviesclean.ui.common.loadImage
import kotlin.properties.Delegates

@SuppressLint("NotifyDataSetChanged")
class SearchAdapter(val itemClickedListener: (movie: Movie) -> Unit): RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var movies: List<Movie> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            itemClickedListener(movie)
        }

    }

    override fun getItemCount(): Int = movies.size

    class ViewHolder(private val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {
                txtTitle.text = movie.title
                imgPoster.loadImage("https://image.tmdb.org/t/p/w185${movie.posterPath ?: movie.backdropPath}")
            }
        }
    }

}