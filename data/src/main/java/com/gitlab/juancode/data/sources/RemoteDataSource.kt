package com.gitlab.juancode.data.sources

import com.gitlab.juancode.domain.Movie

interface RemoteDataSource {
    suspend fun getPopularMovies(apiKey: String): List<Movie>
}