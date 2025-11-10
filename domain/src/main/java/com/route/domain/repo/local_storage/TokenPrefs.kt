package com.route.domain.repo.local_storage

interface TokenPrefs {
    suspend fun getToken(): String?
    suspend fun saveToken(token: String)
}
