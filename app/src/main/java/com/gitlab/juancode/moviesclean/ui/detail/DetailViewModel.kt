package com.gitlab.juancode.moviesclean.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gitlab.juancode.domain.Movie
import com.gitlab.juancode.moviesclean.ui.common.ScopeViewModel
import com.gitlab.juancode.usecases.FindMovieById
import kotlinx.coroutines.launch

class DetailViewModel(private val idMovie: Int, private val findMovieById: FindMovieById) : ScopeViewModel() {

    class UiModel(val movie: Movie)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findMovie()
            return _model
        }

    private fun findMovie() = launch {
        _model.value = UiModel(findMovieById.invoke(idMovie))
    }

    fun onFavoriteClicked() {

    }
}