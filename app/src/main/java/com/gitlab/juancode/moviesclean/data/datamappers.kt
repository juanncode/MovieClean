package com.gitlab.juancode.moviesclean.data

import com.gitlab.juancode.domain.Movie
import com.gitlab.juancode.moviesclean.data.service.models.Movie as ServiceMovie

fun ServiceMovie.toDomainMovie(): Movie  = Movie(
    id,
    title,
    overview,
    releaseDate,
    posterPath?:"",
    backdropPath ?: posterPath ?: "",
    originalLanguage,
    originalTitle,
    popularity,
    voteAverage,
    false
)

fun Movie.toServiceMovie(): ServiceMovie = ServiceMovie(
    id = id,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    posterPath = posterPath,
    backdropPath = backdropPath ?: posterPath,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    popularity = popularity,
    voteAverage = voteAverage,
    adult = false,
    genreIds = listOf(),
    video = false,
    voteCount = 0
)
