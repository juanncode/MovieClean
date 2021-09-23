package com.gitlab.juancode.data.repositories

interface PermissionCheckerApp {

    enum class Permission { COARSE_LOCATION}

    suspend fun check(permission: Permission): Boolean
}