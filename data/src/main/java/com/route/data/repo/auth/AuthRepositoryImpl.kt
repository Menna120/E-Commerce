package com.route.data.repo.auth

import com.route.data.repo.di.IoDispatcher
import com.route.domain.base.Resource
import com.route.domain.entity.User
import com.route.domain.repo.auth.AuthRemoteDataSource
import com.route.domain.repo.auth.AuthRepository
import com.route.domain.repo.local_storage.TokenPrefs
import com.route.domain.repo.local_storage.UserPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userPrefs: UserPrefs,
    private val tokenPrefs: TokenPrefs,
    private val authRemoteDataSource: AuthRemoteDataSource,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AuthRepository {
    override suspend fun login(email: String, password: String): Flow<Resource<String?>> =
        withContext(ioDispatcher) {
            authRemoteDataSource.login(
                email = email,
                password = password
            ).onEach { response ->
                if (response is Resource.Success) {
                    response.data?.let { tokenPrefs.saveToken(it) }
                    userPrefs.saveUser(User(email = email, password = password))
                }
            }
        }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        rePassword: String,
        phone: String
    ): Flow<Resource<String?>> = withContext(ioDispatcher) {
        authRemoteDataSource.register(
            name = name,
            email = email,
            password = password,
            rePassword = rePassword,
            phone = phone
        ).onEach { response ->
            if (response is Resource.Success) {
                response.data?.let { tokenPrefs.saveToken(it) }
                userPrefs.saveUser(
                    User(
                        name = name,
                        email = email,
                        phone = phone,
                        password = password
                    )
                )
            }
        }
    }

    override suspend fun forgetPassword(email: String): Flow<Resource<String?>> =
        withContext(ioDispatcher) { authRemoteDataSource.forgetPassword(email = email) }

    override suspend fun verifyResetCode(resetCode: String): Flow<Resource<String?>> =
        withContext(ioDispatcher) { authRemoteDataSource.verifyResetCode(resetCode = resetCode) }

    override suspend fun resetPassword(
        email: String,
        newPassword: String
    ): Flow<Resource<String?>> = withContext(ioDispatcher) {
        authRemoteDataSource.resetPassword(
            email = email,
            newPassword = newPassword
        ).onEach { response ->
            if (response is Resource.Success) {
                response.data?.let { tokenPrefs.saveToken(it) }
            }
        }
    }

    override suspend fun updatePassword(
        token: String,
        currentPassword: String,
        password: String,
        rePassword: String
    ): Flow<Resource<String?>> = withContext(ioDispatcher) {
        authRemoteDataSource.updatePassword(
            token = token,
            currentPassword = currentPassword,
            password = password,
            rePassword = rePassword
        ).onEach { response ->
            if (response is Resource.Success) {
                response.data?.let { tokenPrefs.saveToken(it) }
            }
        }
    }

    override suspend fun updateUser(
        token: String,
        name: String?,
        email: String?,
        phone: String?
    ): Flow<Resource<Unit>> = withContext(ioDispatcher) {
        authRemoteDataSource.updateUser(
            token = token,
            name = name,
            email = email,
            phone = phone,
        ).onEach { response ->
            if (response is Resource.Success) {
                userPrefs.saveUser(User(name = name, email = email, phone = phone))
            }
        }
    }
}
