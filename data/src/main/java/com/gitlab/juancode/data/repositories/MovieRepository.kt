package com.gitlab.juancode.data.repositories

import com.gitlab.juancode.data.sources.RemoteDataSource
import com.gitlab.juancode.domain.Movie

class MovieRepository(private val remoteDataSource: RemoteDataSource, private val apiKey: String) {
    suspend fun getPopularMovies(): List<Movie> {
        return remoteDataSource.getPopularMovies(apiKey)
    }
}