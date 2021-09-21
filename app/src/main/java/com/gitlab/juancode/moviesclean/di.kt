package com.gitlab.juancode.moviesclean

import android.app.Application
import com.gitlab.juancode.moviesclean.data.database.MovieDatabase
import com.gitlab.juancode.moviesclean.ui.main.MainFragment
import com.gitlab.juancode.moviesclean.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, scopesModule))
    }
}
private val appModule = module {
    single { MovieDatabase.build(androidContext()) }
}

private val scopesModule = module {
    scope(named<MainFragment>()) {
        viewModel { MainViewModel() }
    }
}