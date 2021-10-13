package com.gitlab.juancode.moviesclean.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    fun getMovies(): List<Movie>

    @Query("SELECT * FROM Movie WHERE id=:id")
    fun findById(id:Int): Movie

    @Query("SELECT COUNT(id) FROM Movie")
    fun movieCount(): Int

    @Insert
    fun saveAllMovies(movies: List<Movie>)

    @Insert
    fun saveMovie(movie: Movie)

    @Update
    fun updateMovie(movie: Movie)

}