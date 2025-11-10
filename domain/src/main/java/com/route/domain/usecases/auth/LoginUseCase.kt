package com.route.domain.usecases.auth

import com.route.domain.repo.auth.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repo: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) = repo.login(
        email = email,
        password = password
    )
}
