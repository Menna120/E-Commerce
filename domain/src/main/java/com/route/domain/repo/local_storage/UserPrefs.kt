package com.route.domain.repo.local_storage

import com.route.domain.entity.User

interface UserPrefs {
    suspend fun getUser(): User
    suspend fun saveUser(user: User)
}
