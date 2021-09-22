package com.gitlab.juancode.moviesclean.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gitlab.juancode.domain.Movie
import com.gitlab.juancode.moviesclean.ui.common.Event
import com.gitlab.juancode.moviesclean.ui.common.ScopeViewModel
import com.gitlab.juancode.usecases.GetPopularMovies
import kotlinx.coroutines.launch

class MainViewModel(private val getPopularMovies: GetPopularMovies): ScopeViewModel() {

    sealed class UiMovie {
        object Loading : UiMovie()
        class Data(val movies: List<Movie>): UiMovie()
    }
    private val _modelMovie = MutableLiveData<UiMovie>()
    val modelMovie: LiveData<UiMovie>
        get() {
            if (_modelMovie.value == null) loadMovies()
            return _modelMovie
        }

    private val _navigateToMovie = MutableLiveData<Event<Int>>()
    val navigateToMovie: LiveData<Event<Int>> get() = _navigateToMovie

    fun loadMovies() {
        launch {
            _modelMovie.value = UiMovie.Data(getPopularMovies.invoke())
        }
    }

    fun onItemClicked(movie: Movie) {
        _navigateToMovie.value = Event(movie.id)
    }
}