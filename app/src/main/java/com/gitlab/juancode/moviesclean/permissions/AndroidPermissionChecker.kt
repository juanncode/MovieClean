package com.gitlab.juancode.moviesclean.permissions

import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.gitlab.juancode.data.repositories.PermissionCheckerApp

class AndroidPermissionChecker(private val application: Application) : PermissionCheckerApp {
    override suspend fun check(permission: PermissionCheckerApp.Permission): Boolean {
        return ContextCompat.checkSelfPermission(
            application,
            permission.toAndroidId()
        ) == PackageManager.PERMISSION_GRANTED
    }
}

private fun PermissionCheckerApp.Permission.toAndroidId(): String = when (this) {
    PermissionCheckerApp.Permission.COARSE_LOCATION -> android.Manifest.permission.ACCESS_COARSE_LOCATION
}