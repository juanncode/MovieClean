package com.gitlab.juancode.usecases

import com.gitlab.juancode.data.repositories.MovieRepository
import com.gitlab.juancode.domain.Movie

class FindMovieById(private val movieRepository: MovieRepository) {

    suspend fun invoke(id: Int): Movie  = movieRepository.findById(id)

}