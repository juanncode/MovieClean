package com.gitlab.juancode.usecases

import com.gitlab.juancode.data.repositories.MovieRepository
import com.gitlab.juancode.domain.Movie

class GetSearchMovies(private val movieRepository: MovieRepository) {

    suspend fun invoke(query: String): List<Movie> {
        return movieRepository.getSearchMovies(query)
    }
}