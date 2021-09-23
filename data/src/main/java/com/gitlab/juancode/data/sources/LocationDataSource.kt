package com.gitlab.juancode.data.sources

interface LocationDataSource {
    suspend fun findLastLocation(): String
}