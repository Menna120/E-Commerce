package com.route.domain.usecases.auth

import com.route.domain.repo.auth.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repo: AuthRepository) {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        rePassword: String,
        phone: String
    ) = repo.register(
        name = name,
        email = email,
        password = password,
        rePassword = rePassword,
        phone = phone
    )
}
