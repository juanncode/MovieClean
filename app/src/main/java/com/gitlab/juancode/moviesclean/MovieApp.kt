package com.gitlab.juancode.moviesclean

import android.app.Application

class MovieApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}