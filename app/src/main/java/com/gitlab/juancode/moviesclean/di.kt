package com.gitlab.juancode.moviesclean

import android.app.Application
import android.app.SearchManager
import com.gitlab.juancode.data.repositories.MovieRepository
import com.gitlab.juancode.data.repositories.PermissionCheckerApp
import com.gitlab.juancode.data.repositories.RegionRepository
import com.gitlab.juancode.data.sources.LocationDataSource
import com.gitlab.juancode.data.sources.RemoteDataSource
import com.gitlab.juancode.moviesclean.data.PlayServicesLocationDataSource
import com.gitlab.juancode.moviesclean.data.database.MovieDatabase
import com.gitlab.juancode.moviesclean.data.service.MovieDbDataSource
import com.gitlab.juancode.moviesclean.permissions.AndroidPermissionChecker
import com.gitlab.juancode.moviesclean.ui.main.MainFragment
import com.gitlab.juancode.moviesclean.ui.main.MainViewModel
import com.gitlab.juancode.moviesclean.ui.search.SearchFragment
import com.gitlab.juancode.moviesclean.ui.search.SearchViewModel
import com.gitlab.juancode.usecases.GetPopularMovies
import com.gitlab.juancode.usecases.GetSearchMovies
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
    factory<LocationDataSource> { PlayServicesLocationDataSource(get()) }
    factory<PermissionCheckerApp> { AndroidPermissionChecker(get()) }
}

private val dataModule = module {
    factory { MovieRepository(get(), get(), get(named("apiKey"))) }
    factory { RegionRepository(get(), get()) }
}

private val scopesModule = module {
    scope(named<MainFragment>()) {
        viewModel { MainViewModel(get()) }
        scoped { GetPopularMovies(get()) }
    }

    scope(named<SearchFragment>()) {
        viewModel { SearchViewModel(get()) }
        scoped { GetSearchMovies(get()) }
    }
}