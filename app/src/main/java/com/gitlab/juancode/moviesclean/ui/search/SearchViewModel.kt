package com.gitlab.juancode.moviesclean.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gitlab.juancode.domain.Movie
import com.gitlab.juancode.moviesclean.ui.common.Event
import com.gitlab.juancode.moviesclean.ui.common.ScopeViewModel
import com.gitlab.juancode.usecases.GetSearchMovies
import com.gitlab.juancode.usecases.SaveMovie
import kotlinx.coroutines.launch

class SearchViewModel(private val getSearchMovies: GetSearchMovies, private val saveMovie: SaveMovie) : ScopeViewModel() {

    sealed class SearchModel {
        object Loading : SearchModel()
        object Close : SearchModel()
        class Data(val data: List<Movie>): SearchModel()
    }

    private val _navigation = MutableLiveData<Event<Movie>>()
    val navigation: LiveData<Event<Movie>> get() = _navigation

    private val _model = MutableLiveData<SearchModel>()
    val model: LiveData<SearchModel> get() = _model

    fun loadMovies(query: String) {
        launch {
            val data = getSearchMovies.invoke(query)
            _model.value = SearchModel.Loading
            _model.value = SearchModel.Data(data)
        }
    }

    fun saveMovie(movie: Movie) {
        launch {
            saveMovie.invoke(movie)
            _navigation.value = Event(movie)
        }
    }

    fun onBackClicked() {
        _model.value = SearchModel.Close
    }
}