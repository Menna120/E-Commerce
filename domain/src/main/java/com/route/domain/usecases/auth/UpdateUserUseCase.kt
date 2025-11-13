package com.route.domain.usecases.auth

import com.route.domain.repo.auth.AuthRepository
import com.route.domain.repo.local_storage.TokenPrefs
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val repo: AuthRepository,
    private val tokenPrefs: TokenPrefs
) {
    suspend operator fun invoke(
        name: String?,
        email: String?,
        phone: String?
    ) = repo.updateUser(
        token = tokenPrefs.getToken() ?: "",
        name = name,
        email = email,
        phone = phone
    )
}
