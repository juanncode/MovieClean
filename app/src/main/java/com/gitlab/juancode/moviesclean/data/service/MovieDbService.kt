package com.gitlab.juancode.moviesclean.data.service

import com.gitlab.juancode.moviesclean.data.service.models.MovieDbResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbService {

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun listPopularMoviesAsync(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): MovieDbResult

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): MovieDbResult
}