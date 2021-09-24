package com.gitlab.juancode.moviesclean.data.service

import com.gitlab.juancode.data.sources.RemoteDataSource
import com.gitlab.juancode.domain.Movie
import com.gitlab.juancode.moviesclean.data.toDomainMovie

class MovieDbDataSource: RemoteDataSource {
    override suspend fun getPopularMovies(apiKey: String, region: String): List<Movie> {
        return MovieDbRetrofit.service.listPopularMoviesAsync(apiKey, region).results.map { it.toDomainMovie() }
    }

    override suspend fun searchMovies(apiKey: String, query: String): List<Movie> {
        val res = MovieDbRetrofit.service.searchMovies(apiKey, query)
        return res.results.map { it.toDomainMovie() }

    }
}


