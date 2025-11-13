package com.route.domain.usecases.auth

import com.route.domain.repo.auth.AuthRepository
import com.route.domain.repo.local_storage.TokenPrefs
import javax.inject.Inject

class UpdatePasswordUseCase @Inject constructor(
    private val repo: AuthRepository,
    private val tokenPrefs: TokenPrefs
) {
    suspend operator fun invoke(
        currentPassword: String,
        password: String
    ) = repo.updatePassword(
        token = tokenPrefs.getToken() ?: "",
        currentPassword = currentPassword,
        password = password,
        rePassword = password
    )
}
