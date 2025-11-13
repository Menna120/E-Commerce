package com.route.domain.repo.auth

import com.route.domain.base.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRemoteDataSource {
    suspend fun login(email: String, password: String): Flow<Resource<String?>>
    suspend fun register(
        name: String,
        email: String,
        password: String,
        rePassword: String,
        phone: String
    ): Flow<Resource<String?>>

    suspend fun forgetPassword(email: String): Flow<Resource<String?>>
    suspend fun verifyResetCode(resetCode: String): Flow<Resource<String?>>
    suspend fun resetPassword(email: String, newPassword: String): Flow<Resource<String?>>
    suspend fun updatePassword(
        token: String,
        currentPassword: String,
        password: String,
        rePassword: String
    ): Flow<Resource<String?>>

    suspend fun updateUser(
        token: String,
        name: String?,
        email: String?,
        phone: String?
    ): Flow<Resource<Unit>>
}
