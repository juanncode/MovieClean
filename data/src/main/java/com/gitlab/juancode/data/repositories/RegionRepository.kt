package com.gitlab.juancode.data.repositories

import com.gitlab.juancode.data.sources.LocationDataSource

class RegionRepository(
    private val locationDataSource: LocationDataSource,
    private val permissionCheckerApp: PermissionCheckerApp
) {
    companion object {
        private const val DEFAULT_REGION = "US"
    }

    suspend fun findLastRegion(): String {
        return if (permissionCheckerApp.check(PermissionCheckerApp.Permission.COARSE_LOCATION)) {
            locationDataSource.findLastLocation()
        } else {
            DEFAULT_REGION
        }
    }
}