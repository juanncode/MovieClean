package com.gitlab.juancode.moviesclean.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gitlab.juancode.domain.Movie
import com.gitlab.juancode.moviesclean.R
import kotlin.properties.Delegates

@SuppressLint("NotifyDataSetChanged")
class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var movies: List<Movie> by Delegates.observable(emptyList()) {_,_,_ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) {

        }
    }
}