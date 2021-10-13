package com.gitlab.juancode.data.repositories

import com.gitlab.juancode.data.sources.LocalDataSource
import com.gitlab.juancode.data.sources.RemoteDataSource
import com.gitlab.juancode.domain.Movie

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val regionRepository: RegionRepository,
    private val apiKey: String
) {
    suspend fun getPopularMovies(): List<Movie> {
        if (localDataSource.isEmpty()) {
            val movies = remoteDataSource.getPopularMovies(apiKey, regionRepository.findLastRegion())
            localDataSource.saveAllMovie(movies)

        }
        return localDataSource.getAllMovies()
    }

    suspend fun getSearchMovies(query: String): List<Movie> {
        return remoteDataSource.searchMovies(apiKey, query)
    }
}