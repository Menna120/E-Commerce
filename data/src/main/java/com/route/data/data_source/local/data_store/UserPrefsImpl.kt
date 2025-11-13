package com.route.data.data_source.local.data_store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.route.domain.entity.User
import com.route.domain.repo.local_storage.UserPrefs
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPrefsImpl @Inject constructor(
    private val preferences: DataStore<Preferences>
) : UserPrefs {

    private object PreferencesKeys {
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val USER_PHONE = stringPreferencesKey("user_phone")
        val USER_PASSWORD = stringPreferencesKey("user_password")
    }

    override suspend fun saveUser(user: User) {
        preferences.edit { prefs ->
            user.name?.let { prefs[PreferencesKeys.USER_NAME] = it }
            user.email?.let { prefs[PreferencesKeys.USER_EMAIL] = it }
            user.phone?.let { prefs[PreferencesKeys.USER_PHONE] = it }
            user.password?.let { prefs[PreferencesKeys.USER_PASSWORD] = it }
        }
    }

    override suspend fun getUser(): User {
        return try {
            preferences.data.map { prefs ->
                val name = prefs[PreferencesKeys.USER_NAME]
                val email = prefs[PreferencesKeys.USER_EMAIL]
                val phone = prefs[PreferencesKeys.USER_PHONE]
                val password = prefs[PreferencesKeys.USER_PASSWORD]

                User(name = name, email = email, phone = phone, password = password)
            }.first()
        } catch (e: Exception) {
            e.printStackTrace()
            User()
        }
    }
}
