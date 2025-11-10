package com.route.domain.usecases.auth

import com.route.domain.repo.auth.AuthRepository
import javax.inject.Inject

class ForgetPasswordUseCase @Inject constructor(private val repo: AuthRepository) {
    suspend operator fun invoke(email: String) = repo.forgetPassword(email = email)
}
