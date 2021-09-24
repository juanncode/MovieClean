package com.gitlab.juancode.data.sources

import com.gitlab.juancode.domain.Movie

interface RemoteDataSource {
    suspend fun getPopularMovies(apiKey: String, region: String): List<Movie>
    suspend fun searchMovies(apiKey: String, query: String): List<Movie>
}