package com.route.domain.usecases.local_storage

import com.route.domain.repo.local_storage.TokenPrefs
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(private val prefs: TokenPrefs) {
    suspend operator fun invoke(): String? = prefs.getToken()
}
