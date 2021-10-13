package com.gitlab.juancode.moviesclean.data.database

import com.gitlab.juancode.data.sources.LocalDataSource
import com.gitlab.juancode.domain.Movie
import com.gitlab.juancode.moviesclean.data.toDatabaseMovie
import com.gitlab.juancode.moviesclean.data.toDomainMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: MovieDatabase) : LocalDataSource {

    private val movieDao = db.movieDao()
    override suspend fun getAllMovies(): List<Movie> = withContext(Dispatchers.IO) {
        movieDao.getMovies().map { it.toDomainMovie() }
    }

    override suspend fun getById(id: Int): Movie = withContext(Dispatchers.IO) {
        movieDao.findById(id).toDomainMovie()
    }


    override suspend fun saveAllMovie(movies: List<Movie>) = withContext(Dispatchers.IO) {
        movieDao.saveAllMovies(movies.map { it.toDatabaseMovie() })
    }

    override suspend fun saveMovie(movie: Movie) = withContext(Dispatchers.IO) {
        movieDao.saveMovie(movie.toDatabaseMovie())
    }

    override suspend fun updateMovie(movie: Movie) = withContext(Dispatchers.IO) {
        movieDao.updateMovie(movie.toDatabaseMovie())
    }

    override suspend fun isEmpty(): Boolean = withContext(Dispatchers.IO) {
        movieDao.movieCount() <= 0
    }

}