package com.route.data.data_source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.route.domain.repo.local_storage.TokenPrefs
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenPrefsImpl @Inject constructor(
    private val preferences: DataStore<Preferences>
) : TokenPrefs {
    companion object {
        const val TOKEN_KEY = "token"
    }

    override suspend fun getToken(): String? = try {
        preferences.data.map { prefs -> prefs[stringPreferencesKey(TOKEN_KEY)] }.first()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    override suspend fun saveToken(token: String) {
        preferences.edit { prefs -> prefs[stringPreferencesKey(TOKEN_KEY)] = token }
    }

}
