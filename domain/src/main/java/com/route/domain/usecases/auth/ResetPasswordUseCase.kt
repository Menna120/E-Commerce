package com.route.domain.usecases.auth

import com.route.domain.repo.auth.AuthRepository
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(private val repo: AuthRepository) {
    suspend operator fun invoke(email: String, newPassword: String) =
        repo.resetPassword(email = email, newPassword = newPassword)
}
