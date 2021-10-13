package com.gitlab.juancode.data.sources

import com.gitlab.juancode.domain.Movie

interface LocalDataSource {

    suspend fun getAllMovies(): List<Movie>
    suspend fun saveAllMovie(movies: List<Movie>)
    suspend fun saveMovie(movie: Movie)
    suspend fun updateMovie(movie: Movie)
    suspend fun isEmpty(): Boolean
}