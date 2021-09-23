package com.gitlab.juancode.moviesclean.data

import android.annotation.SuppressLint
import android.app.Application
import android.location.Geocoder
import android.location.Location
import com.gitlab.juancode.data.sources.LocationDataSource
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class PlayServicesLocationDataSource(application: Application) : LocationDataSource {
    private val geocoder = Geocoder(application)
    private val fusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    @SuppressLint("MissingPermission")
    override suspend fun findLastLocation(): String =
        suspendCancellableCoroutine { continuation ->
            fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                continuation.resume(getRegionFromLocation(it.result))
            }
        }


    private fun getRegionFromLocation(location: Location?): String {
        if (location == null) return "US"

        val result = geocoder.getFromLocation(
            location.latitude,
            location.longitude,
            1
        )
        return result.firstOrNull()?.countryCode ?: "US"
    }
}