package com.route.domain.usecases.auth

import com.route.domain.repo.auth.AuthRepository
import javax.inject.Inject

class VerifyResetCodeUseCase @Inject constructor(private val repo: AuthRepository) {
    suspend operator fun invoke(resetCode: String) =
        repo.verifyResetCode(resetCode = resetCode)
}
