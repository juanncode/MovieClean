package com.gitlab.juancode.usecases

import com.gitlab.juancode.data.repositories.MovieRepository
import com.gitlab.juancode.domain.Movie

class GetPopularMovies(private val movieRepository: MovieRepository) {
    suspend fun invoke() : List<Movie> = movieRepository.getPopularMovies()
}