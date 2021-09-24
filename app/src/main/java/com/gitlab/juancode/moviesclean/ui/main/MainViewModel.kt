package com.gitlab.juancode.moviesclean.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
        get() = _modelMovie

    private val _navigateToMovie = MutableLiveData<Event<Movie>>()
    val navigateToMovie: LiveData<Event<Movie>> get() = _navigateToMovie

    private val _navigateToSearch = MutableLiveData<Event<Unit>>()
    val navigateToSearch: LiveData<Event<Unit>> get() = _navigateToSearch

    private val _requestPermission = MutableLiveData<Event<Unit>>()
    val requestPermission: LiveData<Event<Unit>> get() = _requestPermission

    init {
        loadPermission()
    }

    private fun loadPermission() {
        _requestPermission.value = Event(Unit)
    }

    private fun loadMovies() {
        launch {
            _modelMovie.value = UiMovie.Data(getPopularMovies.invoke())
        }
    }

    fun onItemClicked(movie: Movie) {
        _navigateToMovie.value = Event(movie)
    }

    fun onCoarsePermissionRequested() {
        loadMovies()
    }

    fun onSearchIconClick() {
        _navigateToSearch.value = Event(Unit)
    }
}