package com.gitlab.juancode.usecases

import com.gitlab.juancode.data.repositories.MovieRepository
import com.gitlab.juancode.domain.Movie

class SaveMovie(private val movieRepository: MovieRepository) {
    suspend fun invoke(movie: Movie) {
        movieRepository.saveMovie(movie)
    }
}