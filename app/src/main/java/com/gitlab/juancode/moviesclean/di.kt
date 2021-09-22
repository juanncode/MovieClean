package com.gitlab.juancode.moviesclean

import android.app.Application
import com.gitlab.juancode.data.repositories.MovieRepository
import com.gitlab.juancode.data.sources.RemoteDataSource
import com.gitlab.juancode.moviesclean.data.database.MovieDatabase
import com.gitlab.juancode.moviesclean.data.service.MovieDbDataSource
import com.gitlab.juancode.moviesclean.ui.main.MainFragment
import com.gitlab.juancode.moviesclean.ui.main.MainViewModel
import com.gitlab.juancode.usecases.GetPopularMovies
import org.koin.android.ext.koin.androidApplication
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
        modules(listOf(appModule, dataModule, scopesModule))
    }
}
private val appModule = module {
    single(named("apiKey")) { androidApplication().resources.getString(R.string.api_key) }
    single { MovieDatabase.build(androidContext()) }
    factory<RemoteDataSource> { MovieDbDataSource() }
}

private val dataModule = module {
    factory { MovieRepository(get(), get(named("apiKey"))) }
}

private val scopesModule = module {
    scope(named<MainFragment>()) {
        viewModel { MainViewModel(get()) }
        scoped { GetPopularMovies(get()) }
    }
}