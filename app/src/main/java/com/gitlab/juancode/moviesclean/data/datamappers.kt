package com.gitlab.juancode.moviesclean.data

import com.gitlab.juancode.domain.Movie
import com.gitlab.juancode.moviesclean.data.service.models.Movie as ServiceMovie

fun ServiceMovie.toDomainMovie(): Movie  = Movie(
    id,
    title,
    overview,
    releaseDate,
    posterPath,
    backdropPath ?: posterPath,
    originalLanguage,
    originalTitle,
    popularity,
    voteAverage,
    false
)
