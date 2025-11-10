package com.route.domain.usecases.local_storage

import com.route.domain.entity.User
import com.route.domain.repo.local_storage.UserPrefs
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val prefs: UserPrefs) {
    suspend operator fun invoke(): User = prefs.getUser()
}
