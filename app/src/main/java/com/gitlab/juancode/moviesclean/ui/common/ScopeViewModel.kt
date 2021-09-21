package com.gitlab.juancode.moviesclean.ui.common

import androidx.lifecycle.ViewModel

abstract class ScopeViewModel : ViewModel(), Scope by Scope.Impl() {

    init {
        initScope()
    }

    override fun onCleared() {
        cancelScope()
        super.onCleared()
    }
}