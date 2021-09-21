package com.gitlab.juancode.moviesclean.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gitlab.juancode.moviesclean.ui.common.ScopeViewModel

class MainViewModel: ScopeViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    fun onClickBtn() {
        _message.value = "hola a todos asdasdasds"
    }
}